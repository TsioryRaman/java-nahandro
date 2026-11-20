import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { TestBed } from '@angular/core/testing';

import { environment } from '../../../environments/environment';
import { AuthService } from './auth.service';

describe('AuthService', () => {
  let service: AuthService;
  let httpTestingController: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule]
    });
    service = TestBed.inject(AuthService);
    httpTestingController = TestBed.inject(HttpTestingController);
    localStorage.clear();
  });

  afterEach(() => {
    httpTestingController.verify();
    localStorage.clear();
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should store the access token on successful login', () => {
    service.login({ email: 'user@example.com', password: 'password123' }).subscribe();

    const request = httpTestingController.expectOne(`${environment.apiBaseUrl}/auth/login`);
    expect(request.request.method).toBe('POST');
    expect(request.request.body).toEqual({
      email: 'user@example.com',
      password: 'password123'
    });

    request.flush({ accessToken: 'token-value', tokenType: 'JWT' });

    expect(service.getToken()).toBe('token-value');
  });

  it('should post the registration details', () => {
    service
      .register({ email: 'user@example.com', username: 'johndoe', password: 'password123' })
      .subscribe();

    const request = httpTestingController.expectOne(`${environment.apiBaseUrl}/auth/register`);
    expect(request.request.method).toBe('POST');
    expect(request.request.body).toEqual({
      email: 'user@example.com',
      username: 'johndoe',
      password: 'password123'
    });

    request.flush(null);
  });

  it('should clear the stored token on logout', () => {
    localStorage.setItem('breakfast.access-token', 'token-value');

    service.logout();

    expect(service.getToken()).toBeNull();
    expect(service.isAuthenticated()).toBeFalse();
  });
});
