import {Component, OnInit} from '@angular/core';
import {BasicProduct} from "../../../entities/product/basic-Product";
import {ProductService} from "../../../services/product/product.service";
import {RouterLink} from "@angular/router";

@Component({
  selector: 'app-show-product-catalog',
  standalone: true,
  imports: [
    RouterLink
  ],
  templateUrl: './show-product-catalog.component.html',
  styleUrl: './show-product-catalog.component.scss'
})
export class ShowProductCatalogComponent implements OnInit {

  products: BasicProduct[] = []

  constructor(private productService: ProductService) { }

  ngOnInit() {
    this.productService.getApprovedAndAvailable().subscribe({
      next: data => {
        this.products = data;
      },
      error: err => console.error(err)
    })
  }
}
