import {Component, OnInit} from '@angular/core';
import {DecimalPipe, Location} from "@angular/common";
import {ActivatedRoute, RouterLink} from "@angular/router";
import {ShoppingCart} from "../../../entities/shopping-cart";
import {ShoppingCartService} from "../../../services/shopping-cart/shopping-cart.service";
import {MessageService} from "../../../services/message.service";
import {DeliveryPackageService} from "../../../services/delivery-package/delivery-package.service";

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
    private deliveryPackageService: DeliveryPackageService,
    private route: ActivatedRoute,
    private messageService: MessageService,
    private location: Location) {}

  ngOnInit() {
    const id = Number(this.route.snapshot.paramMap.get('id'));

    this.deliveryPackageService.getById( id ).subscribe({
      next: data => {
        this.shoppingCart = data;
      },
      error: err => {
        const msg = 'Error al cargar los detalles del paquete'
        console.error(msg, err);
        this.messageService.error(msg);
      }
    })
  }

  goBack() {
   this.location.back();
  }
}
