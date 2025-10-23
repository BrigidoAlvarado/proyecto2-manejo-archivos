import {Component, OnInit} from '@angular/core';
import {DecimalPipe, Location} from "@angular/common";
import {ActivatedRoute, RouterLink} from "@angular/router";
import {ShoppingCart} from "../../../entities/shopping-cart";
import {ShoppingCartService} from "../../../services/shopping-cart/shopping-cart.service";

@Component({
  selector: 'app-display-package-details',
  standalone: true,
    imports: [
        DecimalPipe,
        RouterLink
    ],
  templateUrl: './display-package-details.component.html',
  styleUrl: './display-package-details.component.scss'
})
export class DisplayPackageDetailsComponent implements OnInit {

  shoppingCart!: ShoppingCart;

  constructor(
    private shoppingCartService: ShoppingCartService,
    private route: ActivatedRoute,
    private location: Location) {}

  ngOnInit() {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.shoppingCartService.getShoppingCartById( id ).subscribe(shoppingCart => {
      this.shoppingCart = shoppingCart;
    })
  }

  goBack() {
   this.location.back();
  }
}
