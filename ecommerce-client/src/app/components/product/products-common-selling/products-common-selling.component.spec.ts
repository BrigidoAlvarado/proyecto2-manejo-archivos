import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProductsCommonSellingComponent } from './products-common-selling.component';

describe('ProductsCommonSellingComponent', () => {
  let component: ProductsCommonSellingComponent;
  let fixture: ComponentFixture<ProductsCommonSellingComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ProductsCommonSellingComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProductsCommonSellingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
