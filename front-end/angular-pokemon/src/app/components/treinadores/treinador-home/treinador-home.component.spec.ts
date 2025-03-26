import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TreinadorHomeComponent } from './treinador-home.component';

describe('TreinadorHomeComponent', () => {
  let component: TreinadorHomeComponent;
  let fixture: ComponentFixture<TreinadorHomeComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [TreinadorHomeComponent]
    });
    fixture = TestBed.createComponent(TreinadorHomeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
