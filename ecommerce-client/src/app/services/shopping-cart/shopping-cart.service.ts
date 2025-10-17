import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {ShoppingCart} from "../../entities/shopping-cart";
import {AppConfig} from "../../config/app.constants";

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
}
