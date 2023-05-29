import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditBookviewComponent } from './edit-bookview.component';

describe('EditBookviewComponent', () => {
  let component: EditBookviewComponent;
  let fixture: ComponentFixture<EditBookviewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EditBookviewComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EditBookviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
