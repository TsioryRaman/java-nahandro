import { ComponentFixture, TestBed } from '@angular/core/testing';
import { TranslateLoader, TranslateModule } from '@ngx-translate/core';
import { Observable, of } from 'rxjs';

import { LanguageService } from '../../../core/services/language.service';
import { LanguageSwitcherComponent } from './language-switcher.component';

class FakeTranslateLoader implements TranslateLoader {
  getTranslation(): Observable<Record<string, never>> {
    return of({});
  }
}

describe('LanguageSwitcherComponent', () => {
  let component: LanguageSwitcherComponent;
  let fixture: ComponentFixture<LanguageSwitcherComponent>;
  let languageService: LanguageService;

  beforeEach(async () => {
    localStorage.clear();

    await TestBed.configureTestingModule({
      imports: [
        TranslateModule.forRoot({
          defaultLanguage: 'fr',
          loader: { provide: TranslateLoader, useClass: FakeTranslateLoader }
        })
      ],
      declarations: [LanguageSwitcherComponent]
    }).compileComponents();

    fixture = TestBed.createComponent(LanguageSwitcherComponent);
    component = fixture.componentInstance;
    languageService = TestBed.inject(LanguageService);
    fixture.detectChanges();
  });

  afterEach(() => localStorage.clear());

  it('should default to French', () => {
    expect(languageService.currentLanguage).toBe('fr');
  });

  it('should switch to English and persist the choice', () => {
    component.select('en');

    expect(languageService.currentLanguage).toBe('en');
    expect(localStorage.getItem('breakfast.language')).toBe('en');
  });
});
