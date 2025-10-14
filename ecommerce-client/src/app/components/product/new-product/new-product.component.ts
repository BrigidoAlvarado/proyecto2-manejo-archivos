import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators} from "@angular/forms";
import {MessageService} from "../../../services/message.service";
import {Product} from "../../../entities/product";
import {ProductService} from "../../../services/product/product.service";

@Component({
  selector: 'app-new-product',
  standalone: true,
  imports: [ReactiveFormsModule, FormsModule],
  templateUrl: './new-product.component.html',
  styleUrl: './new-product.component.scss'
})
export class NewProductComponent implements OnInit {

  productForm!: FormGroup;
  selectedImage?: File;

  constructor(
    private fb: FormBuilder,
    private message: MessageService,
    private productService: ProductService,
  ) {
  }

  ngOnInit() {
    this.productForm = this.fb.nonNullable.group({
      name: ['', [Validators.required, Validators.maxLength(50), Validators.minLength(1)]],
      description: ['', Validators.required],
      price: [0, [Validators.required, Validators.min(1)]],
      stock: [0, [Validators.required, Validators.min(0)]],
      isNew: ['true', Validators.required],
    })
  }

  submit() {

    if (this.productForm.invalid) {
      this.message.error('Formulario invalido por favor revise los campos')
      return;
    }

    if (!this.selectedImage) {
      this.message.error('No hay una imagen valida seleccionada')
      return
    }

    const product: Product = {
      ...this.productForm.value,
      image: this.selectedImage,
    }

    product.isNew = this.productForm.get('isNew')?.value === 'true'

    console.log('el valor de isNew es', product.isNew)

    this.productService.post(product).subscribe({
      next: (res) => {

        this.message.success('se registro el producto exitosamente');
        console.log('se registro el producto exitosamente');
        this.productForm.reset();

      },
      error: err => {
        console.log('error al crear un nuevo producto', err);
      }
    })
  }

  isInvalid(controlName: string): boolean {
    const control = this.productForm.get(controlName)
    return !!(control && control.touched && control.invalid);
  }

  onFileSelected(event: any) {
    const file = event.target.files[0];
    if (!file) return

    if (file.type === 'image/jpg') {
      this.message.error('Solo se aceptan imagenes con formato jpeg');
      event.target.value = '';
      return;
    }

    this.selectedImage = file;
  }
}
