import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NoApproveRevisedProductComponent } from './no-approve-revised-product.component';

describe('NoApproveRevisedProductComponent', () => {
  let component: NoApproveRevisedProductComponent;
  let fixture: ComponentFixture<NoApproveRevisedProductComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [NoApproveRevisedProductComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(NoApproveRevisedProductComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
