import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {AppConfig} from "../../config/app.constants";

@Injectable({
  providedIn: 'root'
})
export class PurchaseDetailService {

  private baseUrl: string = `${AppConfig.API_URL}/purchase-detail`;

  constructor(
    private http: HttpClient,
  ) { }

  post( purchaseDetail: { productId: number, amount: number } ):
  Observable<any>{
    return this.http.post( this.baseUrl, purchaseDetail)
  }
}
