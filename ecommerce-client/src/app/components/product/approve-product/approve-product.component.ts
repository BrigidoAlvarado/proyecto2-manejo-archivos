import {Component, OnInit} from '@angular/core';
import {BasicProduct} from "../../../entities/product/basic-Product";
import {ProductService} from "../../../services/product/product.service";
import { RouterLink} from "@angular/router";
import {MessageService} from "../../../services/message.service";
import {FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators} from "@angular/forms";
import {ValidFormService} from "../../../services/tools/validForm/valid-form.service";
import {SanctionService} from "../../../services/sanction/sanction.service";
import {SanctionProduct} from "../../../entities/sanction/sanction-product";


@Component({
  selector: 'app-approve-product',
  standalone: true,
  imports: [
    RouterLink,
    FormsModule,
    ReactiveFormsModule
  ],
  templateUrl: './approve-product.component.html',
  styleUrl: './approve-product.component.scss'
})

class ApproveProductComponent implements OnInit {

  basicProducts: BasicProduct[] = [];
  sanctionForm!: FormGroup;
  productSelected: BasicProduct | null = null;

  constructor(
    private productService: ProductService,
    private message: MessageService,
    private formBuilder: FormBuilder,
    private sanctionService: SanctionService,
    public validForm: ValidFormService
    ) {
  }

  ngOnInit() {
    this.productService.getNoApproved().subscribe(
      noApprovedList => this.basicProducts = noApprovedList );

    this.sanctionForm = this.formBuilder.nonNullable.group({
      reason: ['', [Validators.required],],
      amountDays: ['', [Validators.required, Validators.min(1)]],
    })
  }

  approve(id: number, isApprove: boolean) {
    this.message.info('Procesando espere un momento por favor..');

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

  selectProduct( product: BasicProduct ) {
    this.productSelected = product
  }

  sanction(){
    this.message.info('Procesando espere un momento por favor..');

    if( !this.productSelected ){
      this.message.error('No hay ningun producto selecionado')
      return
    }

    if (this.sanctionForm.valid ){
      let sanction: SanctionProduct = this.sanctionForm.value as SanctionProduct;
      sanction.productId = this.productSelected.id;
      sanction.approveProductRequest = {
        id: this.productSelected!.id,
        isApprove: false
      }
      this.sanctionService.postSanctionProduct(sanction).subscribe({
        next: () => {
          this.sanctionForm.reset()
          this.productSelected = null;
          this.message.success('Se proceso la sancion correctamente')
        },
        error: (error) => {
          const msg: string = 'Error al procesar la sancion'
          console.error(msg, error);
          this.message.error(msg)
        }
        }
      )
    } else {
      this.message.error('El formualario es invalido por favor revice los campos')
    }
  }
}

export default ApproveProductComponent

