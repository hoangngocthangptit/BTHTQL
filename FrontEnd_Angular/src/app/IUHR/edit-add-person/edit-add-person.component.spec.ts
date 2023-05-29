import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditAddPersonComponent } from './edit-add-person.component';

describe('EditAddPersonComponent', () => {
  let component: EditAddPersonComponent;
  let fixture: ComponentFixture<EditAddPersonComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EditAddPersonComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EditAddPersonComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
