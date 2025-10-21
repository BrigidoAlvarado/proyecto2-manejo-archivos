import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserTopProductsApproveComponent } from './user-top-products-approve.component';

describe('UserTopProductsApproveComponent', () => {
  let component: UserTopProductsApproveComponent;
  let fixture: ComponentFixture<UserTopProductsApproveComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [UserTopProductsApproveComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UserTopProductsApproveComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
