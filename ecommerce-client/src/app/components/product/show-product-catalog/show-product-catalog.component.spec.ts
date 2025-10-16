import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShowProductCatalogComponent } from './show-product-catalog.component';

describe('ShowProductCatalogComponent', () => {
  let component: ShowProductCatalogComponent;
  let fixture: ComponentFixture<ShowProductCatalogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ShowProductCatalogComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ShowProductCatalogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
