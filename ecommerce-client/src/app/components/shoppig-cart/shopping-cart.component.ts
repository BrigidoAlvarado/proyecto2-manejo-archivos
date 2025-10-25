import {Component, OnInit} from '@angular/core';
import {ShoppingCart} from "../../entities/shopping-cart";
import {MessageService} from "../../services/message.service";
import {ShoppingCartService} from "../../services/shopping-cart/shopping-cart.service";
import {DecimalPipe} from "@angular/common";
import {RouterLink} from "@angular/router";
import {PurchaseDetail} from "../../entities/purchase-detail";
import {PurchaseDetailService} from "../../services/purchase-detail/purchase-detail.service";

@Component({
  selector: 'app-shopping-cart',
  standalone: true,
  imports: [
    DecimalPipe,
    RouterLink
  ],
  templateUrl: './shopping-cart.component.html',
  styleUrl: './shopping-cart.component.scss'
})
export class ShoppingCartComponent implements OnInit {

  shoppingCart!: ShoppingCart;
  selectedItem: PurchaseDetail | null = null;

  constructor(
    private message: MessageService,
    private shoppingCartService: ShoppingCartService,
    private purchaseDetailService: PurchaseDetailService,
  ) {
  }

  ngOnInit() {
    this.shoppingCartService.getShoppingCart().subscribe({
      next: data => {
        this.shoppingCart = data;
      },
      error: err => {
        console.log('Error al cargar el carrito de compras'+err);
      }
    })
  }

  selectItem(item: PurchaseDetail) {
    this.selectedItem = item;
  }

  protected update(amount: HTMLInputElement) {
    if (!this.selectedItem) return

    this.selectedItem.amount = parseInt(amount.value);

    this.purchaseDetailService.post(this.selectedItem).subscribe({
      next: () => {
        this.message.success('Se actualizo el carrito de compras');
        this.ngOnInit()
      },
      error: err => {
        const msg = 'Error al actualizar el carrito de compras';
        this.message.error(msg);
        console.error(msg, err);
      }
    })
  }

  deleteItem( item: PurchaseDetail ) {
    if(!item) return

    this.purchaseDetailService.delete( item.purchaseDetailId ).subscribe({
      next: () => {
        this.message.success('Se elimino un item del carrito de compras');
        this.ngOnInit()
      },
      error: err => {
        const msg = 'Error al actualizar el carrito de compras';
        this.message.error(msg);
        console.error(msg, err);
      }
    })
  }

  deleteShoppingCartItems() {
    if(!this.shoppingCart) return

    this.shoppingCartService.deleteShoppingCartItems(this.shoppingCart.id).subscribe({
      next: () => {
        this.message.success('Se vacio el carrito de compras');
        this.ngOnInit()
      },
      error: err => {
        const msg = 'Error al vaciar el carrito de compras';
        this.message.error(msg);
        console.error(msg, err);
      }
    })
  }
}
