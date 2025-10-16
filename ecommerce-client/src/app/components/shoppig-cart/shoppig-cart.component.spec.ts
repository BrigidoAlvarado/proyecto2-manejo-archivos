import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShoppigCartComponent } from './shoppig-cart.component';

describe('ShoppigCartComponent', () => {
  let component: ShoppigCartComponent;
  let fixture: ComponentFixture<ShoppigCartComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ShoppigCartComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ShoppigCartComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
