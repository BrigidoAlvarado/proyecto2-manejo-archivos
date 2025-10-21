import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserTopPackagesOrderedComponent } from './user-top-packages-ordered.component';

describe('UserTopPackagesOrderedComponent', () => {
  let component: UserTopPackagesOrderedComponent;
  let fixture: ComponentFixture<UserTopPackagesOrderedComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [UserTopPackagesOrderedComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UserTopPackagesOrderedComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
