import {Component, OnInit} from '@angular/core';
import {ProductReport} from "../../../entities/product/product-report";
import {ProductService} from "../../../services/product/product.service";
import {DecimalPipe} from "@angular/common";
import {RouterLink} from "@angular/router";
import {FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators} from "@angular/forms";
import {ReportRequest} from "../../../entities/report-request";

@Component({
  selector: 'app-top-selling-products',
  standalone: true,
  imports: [
    DecimalPipe,
    RouterLink,
    ReactiveFormsModule,
    FormsModule,
  ],
  templateUrl: './top-selling-products.component.html',
  styleUrl: './top-selling-products.component.scss'
})
export class TopSellingProductsComponent implements OnInit {

  products: ProductReport[] = [];
  reportForm!: FormGroup;

  constructor(
    private productService: ProductService,
    private formBuilder: FormBuilder,
  ) {
  }

  ngOnInit() {

    this.reportForm = this.formBuilder.group({
      startDate: [null],
      endDate: [null],
    })
    this.submit()
  }

  submit() {
    const reportRequest: ReportRequest = this.reportForm.value;
    console.log('submit', reportRequest);

    this.productService.getTopSelling(reportRequest).subscribe({
      next: data => {
        console.log('get success');
        this.products = data},
      error: error => console.error('Error al cargar el reporte de productos mas vendidos',+error),
    })
  }
}
