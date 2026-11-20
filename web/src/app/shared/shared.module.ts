import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { TranslateModule } from '@ngx-translate/core';

import { LanguageSwitcherComponent } from './components/language-switcher/language-switcher.component';

@NgModule({
  declarations: [
    LanguageSwitcherComponent
  ],
  imports: [
    CommonModule,
    TranslateModule
  ],
  exports: [
    LanguageSwitcherComponent,
    TranslateModule
  ]
})
export class SharedModule { }
