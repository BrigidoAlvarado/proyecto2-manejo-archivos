import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {ShoppingCart} from "../../entities/shopping-cart";
import {AppConfig} from "../../config/app.constants";
import {id} from "date-fns/locale";

@Injectable({
  providedIn: 'root'
})
export class ShoppingCartService {

  private url = `${AppConfig.API_URL}/shopping-cart`;

  constructor(
    private http: HttpClient,
  ) { }

  getShoppingCart():
    Observable<ShoppingCart>{
    return this.http.get<ShoppingCart>(this.url);
  }

  getShoppingCartById( id : number)
  : Observable<ShoppingCart>{
    return this.http.get<ShoppingCart>(`${this.url  }/${id}`);
  }

  postPayment( cardRequest: { cardNumber:string, save: boolean }):
    Observable<any>{
    console.log('se envian los datos:',cardRequest);
    return this.http.post(`${this.url}/pay`, cardRequest)
  }

  deleteShoppingCartItems(id:number)
    :Observable<any>{
    return this.http.delete(`${this.url}/items/${id}`);
  }
}
