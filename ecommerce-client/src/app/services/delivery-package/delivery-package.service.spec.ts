import { TestBed } from '@angular/core/testing';

import { DeliveryPackageService } from './delivery-package.service';

describe('DeliveryPackageService', () => {
  let service: DeliveryPackageService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DeliveryPackageService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
