import { Component, OnInit } from '@angular/core';
import { RouterLink } from '@angular/router';
import { BasicProduct } from '../../../entities/product/basic-Product';
import { ProductService } from '../../../services/product/product.service';
import {Modal} from "bootstrap";
import {MessageService} from "../../../services/message.service";
import {PurchaseDetailService} from "../../../services/purchase-detail/purchase-detail.service";

@Component({
  selector: 'app-show-product-catalog',
  standalone: true,
  imports: [RouterLink],
  templateUrl: './show-product-catalog.component.html',
  styleUrl: './show-product-catalog.component.scss'
})
export class ShowProductCatalogComponent implements OnInit {
  products: BasicProduct[] = [];
  productSelected: BasicProduct | null = null;

  constructor(
    private productService: ProductService,
    private message: MessageService,
    private purchaseDetailService: PurchaseDetailService,) {
  }

  ngOnInit() {
    this.productService.getApprovedAndAvailable().subscribe({
      next: (data) => (this.products = data),
      error: (err) => console.error(err)
    });
  }

  selectToProduct(product: BasicProduct) {
    this.productSelected = product;
  }

  addToCart(amountInput: HTMLInputElement) {
    if (!this.productSelected) return;

    const amount = Number(amountInput.value);
    if (amount <= 0) {
      this.message.error('Por favor ingresa una cantidad válida.');
      return;
    }

    this.purchaseDetailService.post( {productId: this.productSelected.id, amount: amount} )
      .subscribe({
        next: () => this.message.success('Producto agregado al carrito de compras exitosamente'),
        error: (err) => console.error(err)
      })

    amountInput.value = '1';
    this.productSelected = null
  }
}
