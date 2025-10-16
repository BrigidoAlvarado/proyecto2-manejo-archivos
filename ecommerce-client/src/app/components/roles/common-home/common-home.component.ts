import { Component } from '@angular/core';
import {ShowProductCatalogComponent} from "../../product/show-product-catalog/show-product-catalog.component";

@Component({
  selector: 'app-common-home',
  standalone: true,
  imports: [
    ShowProductCatalogComponent
  ],
  templateUrl: './common-home.component.html',
  styleUrl: './common-home.component.scss'
})
export class CommonHomeComponent {

}
