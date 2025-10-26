import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShowNotificationsComponent } from './show-notifications.component';

describe('ShowNotificationsComponent', () => {
  let component: ShowNotificationsComponent;
  let fixture: ComponentFixture<ShowNotificationsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ShowNotificationsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ShowNotificationsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
