import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, RouterLink} from "@angular/router";
import {ProductService} from "../../../services/product/product.service";
import {ProductResponse} from "../../../entities/product/product-response";
import {NgClass, NgOptimizedImage} from "@angular/common";
import {AuthService} from "../../../services/auth/auth.service";

@Component({
  selector: 'app-display-product',
  standalone: true,
  imports: [
    NgClass,
    RouterLink,
  ],
  templateUrl: './display-product.component.html',
  styleUrl: './display-product.component.scss'
})
export class DisplayProductComponent implements OnInit {

  product!: ProductResponse;

  constructor(
    private route: ActivatedRoute,
    private productService: ProductService,
    public auth: AuthService,
  ) {
  }

  ngOnInit() {

    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.productService.getById(id).subscribe(
      product => {
        this.product = product
        console.log('se recibio', this.product.name);
      }
    );

  }


}
