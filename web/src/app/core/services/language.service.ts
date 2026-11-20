import { Injectable } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';
import { BehaviorSubject } from 'rxjs';

export type AppLanguage = 'fr' | 'en';

export const SUPPORTED_LANGUAGES: AppLanguage[] = ['fr', 'en'];
export const DEFAULT_LANGUAGE: AppLanguage = 'fr';

const LANGUAGE_STORAGE_KEY = 'breakfast.language';

@Injectable({
  providedIn: 'root'
})
export class LanguageService {
  private readonly currentLanguageSubject = new BehaviorSubject<AppLanguage>(DEFAULT_LANGUAGE);

  readonly currentLanguage$ = this.currentLanguageSubject.asObservable();

  constructor(private readonly translate: TranslateService) { }

  get currentLanguage(): AppLanguage {
    return this.currentLanguageSubject.value;
  }

  init(): void {
    this.translate.setDefaultLang(DEFAULT_LANGUAGE);
    this.setLanguage(this.readStoredLanguage());
  }

  setLanguage(language: AppLanguage): void {
    this.currentLanguageSubject.next(language);
    localStorage.setItem(LANGUAGE_STORAGE_KEY, language);
    document.documentElement.lang = language;
    this.translate.use(language);
  }

  private readStoredLanguage(): AppLanguage {
    const stored = localStorage.getItem(LANGUAGE_STORAGE_KEY) as AppLanguage | null;

    return stored !== null && SUPPORTED_LANGUAGES.includes(stored) ? stored : DEFAULT_LANGUAGE;
  }
}
