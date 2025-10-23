import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DisplayPackageDetailsComponent } from './display-package-details.component';

describe('DisplayPackageDetailsComponent', () => {
  let component: DisplayPackageDetailsComponent;
  let fixture: ComponentFixture<DisplayPackageDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DisplayPackageDetailsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DisplayPackageDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
