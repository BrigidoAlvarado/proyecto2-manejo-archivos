import {Component, OnInit} from '@angular/core';
import {BasicProduct} from "../../../entities/product/basic-Product";
import {ProductService} from "../../../services/product/product.service";
import {Router, RouterLink} from "@angular/router";
import {MessageService} from "../../../services/message.service";

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

  constructor(
    private productService: ProductService,
    private message: MessageService,) {
  }

  ngOnInit() {
    this.productService.getNoApproved().subscribe( noApprovedList => this.basicProducts = noApprovedList );
  }

  approve(id: number, isApprove: boolean) {
    this.productService.patchApprove(
      {
        id: id,
        isApprove: isApprove
      }
    ).subscribe({
      next: () => {
        this.ngOnInit()
        if (isApprove){
          this.message.success('Se aprobo el producto correctamente')
        } else {
          this.message.success('Se rechazo el producto correctamente')
        }
      },
      error: (error) => {
        console.error(error);
        this.message.error('No se pudo aprobar el producto correctamente');
      },
      }
    )
  }
}
