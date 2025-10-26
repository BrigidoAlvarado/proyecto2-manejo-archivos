import {Component, OnInit} from '@angular/core';
import {BasicProduct} from "../../../entities/product/basic-Product";
import {ProductService} from "../../../services/product/product.service";
import {MessageService} from "../../../services/message.service";
import {RouterLink} from "@angular/router";

@Component({
  selector: 'app-products-common-selling',
  standalone: true,
  imports: [
    RouterLink
  ],
  templateUrl: './products-common-selling.component.html',
  styleUrl: './products-common-selling.component.scss'
})
export class ProductsCommonSellingComponent implements OnInit {

  products: BasicProduct[] = []

  constructor(
    private productService: ProductService,
    private messageService: MessageService,
  ) {
  }

  ngOnInit() {
    this.productService.getBasicProductsByUser().subscribe({
      next: data => {
        this.products = data
      },
      error: err => {
        const msg = 'Error al cargar la lista de prouductos creados'
        console.error(msg,err)
        this.messageService.error(msg)
      }
    })
  }

  protected pr(product: BasicProduct) {
    console.log(product)
  }
}
