import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NoRevisedDeliveryPackagesComponent } from './no-revised-delivery-packages.component';

describe('NoRevisedDeliveryPackagesComponent', () => {
  let component: NoRevisedDeliveryPackagesComponent;
  let fixture: ComponentFixture<NoRevisedDeliveryPackagesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [NoRevisedDeliveryPackagesComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(NoRevisedDeliveryPackagesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
