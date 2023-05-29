import { ComponentFixture, TestBed } from '@angular/core/testing';

import { KiLuatComponent } from './ki-luat.component';

describe('KiLuatComponent', () => {
  let component: KiLuatComponent;
  let fixture: ComponentFixture<KiLuatComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ KiLuatComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(KiLuatComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
