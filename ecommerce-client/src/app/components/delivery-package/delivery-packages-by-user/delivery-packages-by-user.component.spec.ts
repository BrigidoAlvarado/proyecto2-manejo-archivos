import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DeliveryPackagesByUserComponent } from './delivery-packages-by-user.component';

describe('DeliveryPackagesByUserComponent', () => {
  let component: DeliveryPackagesByUserComponent;
  let fixture: ComponentFixture<DeliveryPackagesByUserComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DeliveryPackagesByUserComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DeliveryPackagesByUserComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
