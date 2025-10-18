import {Component, OnInit} from '@angular/core';
import {ShoppingCart} from "../../entities/shopping-cart";
import {MessageService} from "../../services/message.service";
import {ShoppingCartService} from "../../services/shopping-cart/shopping-cart.service";
import {DecimalPipe} from "@angular/common";
import {RouterLink} from "@angular/router";

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

  constructor(
    private message: MessageService,
    private shoppingCartService: ShoppingCartService,
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

}
