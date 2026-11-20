import { Component } from '@angular/core';

import { AppLanguage, LanguageService } from '../../../core/services/language.service';

interface LanguageOption {
  code: AppLanguage;
  label: string;
  shortLabel: string;
}

@Component({
  selector: 'app-language-switcher',
  templateUrl: './language-switcher.component.html',
  styleUrls: ['./language-switcher.component.css']
})
export class LanguageSwitcherComponent {
  readonly languages: LanguageOption[] = [
    { code: 'fr', label: 'Français', shortLabel: 'FR' },
    { code: 'en', label: 'English', shortLabel: 'EN' }
  ];

  readonly currentLanguage$ = this.languageService.currentLanguage$;

  constructor(private readonly languageService: LanguageService) { }

  select(language: AppLanguage): void {
    this.languageService.setLanguage(language);
  }
}
