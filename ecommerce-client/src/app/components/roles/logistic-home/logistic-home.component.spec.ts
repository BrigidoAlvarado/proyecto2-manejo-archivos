import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LogisticHomeComponent } from './logistic-home.component';

describe('LogisticHomeComponent', () => {
  let component: LogisticHomeComponent;
  let fixture: ComponentFixture<LogisticHomeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [LogisticHomeComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LogisticHomeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
