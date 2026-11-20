import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { TranslateLoader, TranslateModule } from '@ngx-translate/core';
import { Observable, of } from 'rxjs';

import { SharedModule } from '../../../../shared/shared.module';
import { DashboardComponent } from './dashboard.component';

class FakeTranslateLoader implements TranslateLoader {
  getTranslation(): Observable<Record<string, never>> {
    return of({});
  }
}

describe('DashboardComponent', () => {
  let component: DashboardComponent;
  let fixture: ComponentFixture<DashboardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [
        HttpClientTestingModule,
        RouterTestingModule,
        TranslateModule.forRoot({
          defaultLanguage: 'fr',
          loader: { provide: TranslateLoader, useClass: FakeTranslateLoader }
        }),
        SharedModule
      ],
      declarations: [DashboardComponent]
    }).compileComponents();

    fixture = TestBed.createComponent(DashboardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
