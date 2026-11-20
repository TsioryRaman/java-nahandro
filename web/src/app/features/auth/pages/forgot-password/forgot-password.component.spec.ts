import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ReactiveFormsModule } from '@angular/forms';
import { RouterTestingModule } from '@angular/router/testing';
import { TranslateLoader, TranslateModule } from '@ngx-translate/core';
import { Observable, of } from 'rxjs';

import { SharedModule } from '../../../../shared/shared.module';
import { ForgotPasswordComponent } from './forgot-password.component';

class FakeTranslateLoader implements TranslateLoader {
  getTranslation(): Observable<Record<string, never>> {
    return of({});
  }
}

describe('ForgotPasswordComponent', () => {
  let component: ForgotPasswordComponent;
  let fixture: ComponentFixture<ForgotPasswordComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [
        ReactiveFormsModule,
        RouterTestingModule,
        TranslateModule.forRoot({
          defaultLanguage: 'fr',
          loader: { provide: TranslateLoader, useClass: FakeTranslateLoader }
        }),
        SharedModule
      ],
      declarations: [ForgotPasswordComponent]
    }).compileComponents();

    fixture = TestBed.createComponent(ForgotPasswordComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should show the confirmation once a valid email is submitted', () => {
    component.form.setValue({ email: 'user@example.com' });

    component.submit();

    expect(component.submitted).toBeTrue();
    expect(component.submittedEmail).toBe('user@example.com');
  });
});
