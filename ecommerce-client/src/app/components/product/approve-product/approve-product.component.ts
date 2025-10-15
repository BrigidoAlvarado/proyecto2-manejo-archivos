import {Component, OnInit} from '@angular/core';
import {BasicProduct} from "../../../entities/product/basic-Product";
import {ProductService} from "../../../services/product/product.service";
import {RouterLink} from "@angular/router";

@Component({
  selector: 'app-approve-product',
  standalone: true,
  imports: [
    RouterLink
  ],
  templateUrl: './approve-product.component.html',
  styleUrl: './approve-product.component.scss'
})
export class ApproveProductComponent implements OnInit {

  basicProducts: BasicProduct[] = [];

  constructor(private productService: ProductService) { }

  ngOnInit() {
    this.productService.getNoApproved().subscribe( noApprovedList => this.basicProducts = noApprovedList );
  }
}
