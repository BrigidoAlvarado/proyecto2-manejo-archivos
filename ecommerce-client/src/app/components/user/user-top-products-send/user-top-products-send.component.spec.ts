import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserTopProductsSendComponent } from './user-top-products-send.component';

describe('UserTopProductsSendComponent', () => {
  let component: UserTopProductsSendComponent;
  let fixture: ComponentFixture<UserTopProductsSendComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [UserTopProductsSendComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UserTopProductsSendComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
