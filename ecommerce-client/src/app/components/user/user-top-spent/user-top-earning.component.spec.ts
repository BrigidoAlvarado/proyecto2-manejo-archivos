import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserTopEarningComponent } from './user-top-earning.component';

describe('UserTopSpentComponent', () => {
  let component: UserTopEarningComponent;
  let fixture: ComponentFixture<UserTopEarningComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [UserTopEarningComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UserTopEarningComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
