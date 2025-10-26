import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators} from "@angular/forms";
import {MessageService} from "../../../services/message.service";
import {ProductService} from "../../../services/product/product.service";
import {CategoryService} from "../../../services/category/category.service";
import {Product} from "../../../entities/product/product";
import {ProductResponse} from "../../../entities/product/product-response";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-update-product',
  standalone: true,
  imports: [
    FormsModule,
    ReactiveFormsModule,
  ],
  templateUrl: './update-product.component.html',
  styleUrl: './update-product.component.scss'
})
export class UpdateProductComponent implements OnInit {

  productForm!: FormGroup;
  selectedImage?: File;
  categories: string[] = [];
  selectedCategories: string[] = [];
  product: ProductResponse = {
    id: 0,
    categories: this.categories,
  }

  // Iniciar Servicios
  constructor(
    private fb: FormBuilder,
    private message: MessageService,
    private productService: ProductService,
    private categoryService: CategoryService,
    private activatedRoute: ActivatedRoute,
  ) {
  }

  // Iniciar los filtros del form, traer las categorias
  ngOnInit() {
    const id: number = Number(this.activatedRoute.snapshot.params['id']);
    this.getProduct(id)
    this.getCategories();
    this.initForm()
  }

  initForm() {
    this.productForm = this.fb.nonNullable.group({
      id:           [this.product.id,                       [Validators.required, Validators.min(1)]],
      name:         [this.product.name,                     [Validators.required, Validators.maxLength(50), Validators.minLength(1)]],
      description:  [this.product.description,               Validators.required],
      price:        [this.product.price,                    [Validators.required, Validators.min(1)]],
      stock:        [this.product.stock,                    [Validators.required, Validators.min(0)]],
      isNew:        [this.product.new?'true':'false',        Validators.required],
    })
  }

  getCategories() {
    this.categoryService.get().subscribe({
      next: categoriesFound => {
        this.categories = categoriesFound
      },
      error: () => {
        this.message.error('Category not found');
      }
    });
  }

  getProduct(id: number) {
    this.productService.getById(id).subscribe({
      next: productFound => {
        this.product = productFound
        this.selectedCategories = productFound.categories!
        this.initForm()
      },
      error: err => {
        const msg = 'Error al cargar el producto por id';
        this.message.error(msg);
        console.error(msg, err);
      }
    })
  }

  // Enviar el formulario
  submit() {

    if (this.productForm.invalid) {
      this.message.error('Formulario invalido por favor revise los campos')
      return;
    }

    const product: Product = this.productForm.value as Product;

    product.image = this.selectedImage? this.selectedImage: undefined;
    product.isNew = this.productForm.get('isNew')?.value === 'true'
    product.categories = this.selectedCategories

    console.log('enviando', product)
    this.productService.putProduct(product).subscribe({
      next: () => {

        this.message.success('se actualizo el producto exitosamente');
        this.ngOnInit();
      },
      error: err => {
        const msg = 'Error al actualizar el producto'
        console.error(msg , err);
        this.message.error(msg);
      }
    })
  }

  // Validar si el campo es valido
  isInvalid(controlName: string): boolean {
    const control = this.productForm.get(controlName)
    return !!(control && control.touched && control.invalid);
  }

  // Guardar el archivo seleccionado
  onFileSelected(event: any): void {
    const file = event.target.files[0];
    if (!file) return

    if (file.type === 'image/jpg') {
      this.message.error('Solo se aceptan imagenes con formato jpeg');
      event.target.value = '';
      return;
    }

    this.selectedImage = file;
  }

  // Guardar las categorias seleccionadas
  onCategoryChange(event: Event): void {

    const input = event.target as HTMLInputElement;
    const value = input.value;

    if (input.checked) {
      this.selectedCategories.push(value);
    } else {
      this.selectedCategories.filter(
        cat => cat !== value
      )
    }
  }
}
