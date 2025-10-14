import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {AppConfig} from "../../config/app.constants";
import {Product} from "../../entities/product";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  private apiUrl = `${AppConfig.API_URL}/product`;

  constructor(
    private http: HttpClient,
  ) { }

  post(product: Product): Observable<any>{

    const formData = new FormData();
    formData.append('name', product.name);
    formData.append('description', product.description);
    formData.append( 'price', product.price.toString());
    formData.append('stock', product.stock.toString());
    formData.append('isNew', product.isNew.toString());
    formData.append('image', product.image!);

    console.log('is new en el form data es:',formData.get('isNew'));

    return  this.http.post(this.apiUrl, formData);
  }
}
