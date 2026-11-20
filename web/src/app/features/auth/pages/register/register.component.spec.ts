import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ReactiveFormsModule } from '@angular/forms';
import { RouterTestingModule } from '@angular/router/testing';
import { TranslateLoader, TranslateModule } from '@ngx-translate/core';
import { Observable, of } from 'rxjs';

import { SharedModule } from '../../../../shared/shared.module';
import { RegisterComponent } from './register.component';

class FakeTranslateLoader implements TranslateLoader {
  getTranslation(): Observable<Record<string, never>> {
    return of({});
  }
}

describe('RegisterComponent', () => {
  let component: RegisterComponent;
  let fixture: ComponentFixture<RegisterComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [
        HttpClientTestingModule,
        ReactiveFormsModule,
        RouterTestingModule,
        TranslateModule.forRoot({
          defaultLanguage: 'fr',
          loader: { provide: TranslateLoader, useClass: FakeTranslateLoader }
        }),
        SharedModule
      ],
      declarations: [RegisterComponent]
    }).compileComponents();

    fixture = TestBed.createComponent(RegisterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should flag mismatched passwords', () => {
    component.form.setValue({
      username: 'johndoe',
      email: 'user@example.com',
      password: 'password123',
      confirmPassword: 'different123'
    });

    expect(component.form.hasError('passwordsMismatch')).toBeTrue();
  });

  it('should accept a valid registration form', () => {
    component.form.setValue({
      username: 'johndoe',
      email: 'user@example.com',
      password: 'password123',
      confirmPassword: 'password123'
    });

    expect(component.form.valid).toBeTrue();
  });
});
