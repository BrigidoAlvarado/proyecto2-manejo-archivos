import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PayShoppingCartComponent } from './pay-shopping-cart.component';

describe('PayShoppingCartComponent', () => {
  let component: PayShoppingCartComponent;
  let fixture: ComponentFixture<PayShoppingCartComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PayShoppingCartComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PayShoppingCartComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
