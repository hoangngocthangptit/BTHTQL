import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListPaycheckComponent } from './list-paycheck.component';

describe('ListPaycheckComponent', () => {
  let component: ListPaycheckComponent;
  let fixture: ComponentFixture<ListPaycheckComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListPaycheckComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListPaycheckComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
