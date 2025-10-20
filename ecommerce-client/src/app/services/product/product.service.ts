import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {AppConfig} from "../../config/app.constants";
import {Product} from "../../entities/product/product";
import {Observable} from "rxjs";
import {BasicProduct} from "../../entities/product/basic-Product";
import {ProductResponse} from "../../entities/product/product-response";
import {ProductReport} from "../../entities/product/product-report";

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  private apiUrl: string = `${AppConfig.API_URL}/product`;

  constructor(
    private http: HttpClient,
  ) {
  }

  postNew(product: Product): Observable<any> {

    const formData = new FormData();
    formData.append('name', product.name);
    formData.append('description', product.description);
    formData.append('price', product.price.toString());
    formData.append('stock', product.stock.toString());
    formData.append('isNew', product.isNew.toString());
    formData.append('categories', product.categories.join(','))
    formData.append('image', product.image!);

    console.log('is new en el form data es:', formData.get('isNew'));

    return this.http.post(this.apiUrl, formData);
  }

  getNoApproved(): Observable<BasicProduct[]> {
    return this.http.get<BasicProduct[]>(`${this.apiUrl}/noApproved`);
  }

  getById(id: number): Observable<ProductResponse>{
    return this.http.get<ProductResponse>(`${this.apiUrl}/display/${id}`);
  }

  getTopSelling():
    Observable<ProductReport[]>{
    return this.http.get<ProductReport[]>(`${this.apiUrl}/top/selling`);
  }

  getApprovedAndAvailable(): Observable<BasicProduct[]>{
    return this.http.get<BasicProduct[]>(`${this.apiUrl}/approved/available`);
  }

  patchApprove(id: number): Observable<any>{
    return this.http.patch<any>(`${this.apiUrl}/approve/${id}`, {});
  }
}
