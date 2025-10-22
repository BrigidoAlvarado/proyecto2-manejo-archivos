import {Component, OnInit} from '@angular/core';
import {BasicProduct} from "../../../entities/product/basic-Product";
import {ProductService} from "../../../services/product/product.service";
import {RouterLink} from "@angular/router";
import {FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators} from "@angular/forms";
import {ValidFormService} from "../../../services/tools/validForm/valid-form.service";
import {MessageService} from "../../../services/message.service";

@Component({
  selector: 'app-no-approve-revised-product',
  standalone: true,
  imports: [
    RouterLink,

  ],
  templateUrl: './no-approve-revised-product.component.html',
  styleUrl: './no-approve-revised-product.component.scss'
})
export class NoApproveRevisedProductComponent implements OnInit {

  products: BasicProduct[] = []
  productSelected: BasicProduct | null = null;
  sanctionForm!: FormGroup;

  constructor(
    private productService: ProductService,
    private formBuilder: FormBuilder,
    private message: MessageService,
    public validForm: ValidFormService,
  ) {
  }

  ngOnInit() {


   this.getProducts()
  }

  sanction() {
    if (this.sanctionForm.invalid) {

    } else {
      this.message.error('Formulario invalido por favor revise los campos')
    }
  }

  getProducts(){
    this.productService.getNoApproveAnRevised().subscribe({
      next: data => {
        this.products = data;
      },
      error: err => {
        const msg = 'Error al cargar la lista de productos rechazados'
        console.error(msg + err);
      }
    })
  }

  selectProduct(product: BasicProduct) {
    this.productSelected = product;
  }
}
