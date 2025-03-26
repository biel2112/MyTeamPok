import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LoginTreinadorComponent } from './login-treinador.component';

describe('LoginTreinadorComponent', () => {
  let component: LoginTreinadorComponent;
  let fixture: ComponentFixture<LoginTreinadorComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [LoginTreinadorComponent]
    });
    fixture = TestBed.createComponent(LoginTreinadorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
