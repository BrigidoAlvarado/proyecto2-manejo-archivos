import {Component, OnInit} from '@angular/core';
import {ProductReport} from "../../../entities/product/product-report";
import {ProductService} from "../../../services/product/product.service";
import {DecimalPipe} from "@angular/common";
import {RouterLink} from "@angular/router";

@Component({
  selector: 'app-top-selling-products',
  standalone: true,
  imports: [
    DecimalPipe,
    RouterLink
  ],
  templateUrl: './top-selling-products.component.html',
  styleUrl: './top-selling-products.component.scss'
})
export class TopSellingProductsComponent implements OnInit {

  products: ProductReport[] = [];

  constructor(
    private productService: ProductService,
  ) {
  }

  ngOnInit() {
    this.productService.getTopSelling().subscribe({
      next: data => { this.products = data},
      error: error => console.error('Error al cargar el reporte de productos mas vendidos',+error),
    })
  }
}
