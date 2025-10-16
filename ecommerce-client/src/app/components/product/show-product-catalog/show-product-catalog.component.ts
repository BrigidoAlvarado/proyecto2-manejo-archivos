import {Component, OnInit} from '@angular/core';
import {BasicProduct} from "../../../entities/product/basic-Product";
import {ProductService} from "../../../services/product/product.service";
import {RouterLink} from "@angular/router";
import {ReactiveFormsModule} from "@angular/forms";

@Component({
  selector: 'app-show-product-catalog',
  standalone: true,
  imports: [
    RouterLink,
    ReactiveFormsModule
  ],
  templateUrl: './show-product-catalog.component.html',
  styleUrl: './show-product-catalog.component.scss'
})
export class ShowProductCatalogComponent implements OnInit {

  products: BasicProduct[] = []
  productSelected!: BasicProduct;

  constructor(private productService: ProductService) { }

  ngOnInit() {
    this.productService.getApprovedAndAvailable().subscribe({
      next: data => {
        this.products = data;
      },
      error: err => console.error(err)
    })
  }

  selectToProduct(product: BasicProduct) {
    this.productSelected = product;
  }

  addToCart(amount: number) {
    if (this.productSelected) {
      
    }
  }
}
