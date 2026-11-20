import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, tap } from 'rxjs';

import { environment } from '../../../environments/environment';
import { AuthResponse, LoginRequest, RegisterRequest } from '../models/auth.model';

const ACCESS_TOKEN_KEY = 'breakfast.access-token';
const AUTH_API_URL = `${environment.apiBaseUrl}/auth`;

interface JwtPayload {
  sub?: string;
  exp?: number;
}

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private readonly currentUserSubject = new BehaviorSubject<string | null>(
    this.readEmailFromStoredToken()
  );

  readonly currentUser$ = this.currentUserSubject.asObservable();

  constructor(private readonly http: HttpClient) { }

  login(credentials: LoginRequest): Observable<AuthResponse> {
    return this.http
      .post<AuthResponse>(`${AUTH_API_URL}/login`, credentials)
      .pipe(tap((response) => this.storeSession(response.accessToken)));
  }

  register(payload: RegisterRequest): Observable<void> {
    return this.http.post<void>(`${AUTH_API_URL}/register`, payload);
  }

  logout(): void {
    localStorage.removeItem(ACCESS_TOKEN_KEY);
    this.currentUserSubject.next(null);
  }

  getToken(): string | null {
    return localStorage.getItem(ACCESS_TOKEN_KEY);
  }

  isAuthenticated(): boolean {
    const token = this.getToken();

    return token !== null && !this.isExpired(token);
  }

  private storeSession(token: string): void {
    localStorage.setItem(ACCESS_TOKEN_KEY, token);
    this.currentUserSubject.next(this.decodePayload(token)?.sub ?? null);
  }

  private readEmailFromStoredToken(): string | null {
    const token = localStorage.getItem(ACCESS_TOKEN_KEY);

    return token ? this.decodePayload(token)?.sub ?? null : null;
  }

  private isExpired(token: string): boolean {
    const expiration = this.decodePayload(token)?.exp;

    return typeof expiration === 'number' && expiration * 1000 <= Date.now();
  }

  private decodePayload(token: string): JwtPayload | null {
    try {
      const payload = token.split('.')[1];
      const normalized = payload.replace(/-/g, '+').replace(/_/g, '/');
      const padded = normalized.padEnd(Math.ceil(normalized.length / 4) * 4, '=');
      const decoded = decodeURIComponent(
        atob(padded)
          .split('')
          .map((char) => '%' + ('00' + char.charCodeAt(0).toString(16)).slice(-2))
          .join('')
      );

      return JSON.parse(decoded) as JwtPayload;
    } catch {
      return null;
    }
  }
}
