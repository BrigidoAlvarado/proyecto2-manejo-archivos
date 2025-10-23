import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ApproveDeliveryPackageComponent } from './approve-delivery-package.component';

describe('ApproveDeliveryPackageComponent', () => {
  let component: ApproveDeliveryPackageComponent;
  let fixture: ComponentFixture<ApproveDeliveryPackageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ApproveDeliveryPackageComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ApproveDeliveryPackageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
