import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CadastroTreinadorComponent } from './cadastro-treinador.component';

describe('CadastroTreinadorComponent', () => {
  let component: CadastroTreinadorComponent;
  let fixture: ComponentFixture<CadastroTreinadorComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CadastroTreinadorComponent]
    });
    fixture = TestBed.createComponent(CadastroTreinadorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
