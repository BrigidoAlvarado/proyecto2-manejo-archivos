import { Injectable } from '@angular/core';
import {AppConfig} from "../../config/app.constants";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {DeliveryPackage} from "../../entities/delivery-package";
import {ShoppingCart} from "../../entities/shopping-cart";

@Injectable({
  providedIn: 'root'
})
export class DeliveryPackageService {

  private url:string = AppConfig.API_URL + '/delivery-package';

  constructor(
    private http: HttpClient,
  ) { }

  getById( id: number)
  :Observable<ShoppingCart>{
    return this.http.get<ShoppingCart>(`${this.url}/${id}`)
  }

  getAllDeliveryPackagesInProgress()
    :Observable<DeliveryPackage[]> {
    return this.http.get<DeliveryPackage[]>(this.url)
  }

  getAllPackagesNoRevised()
  :Observable<DeliveryPackage []>{
    return this.http.get<DeliveryPackage[]>(`${this.url}/no-revised`)
  }

  getAllByUser()
  :Observable<DeliveryPackage[]>{
    return this.http.get<DeliveryPackage[]>(`${this.url}/user`)
  }

  patchDeliverPackage(id:number):
  Observable<any>{
    return this.http.patch<DeliveryPackage>(`${this.url}/deliver/${id}`,{})
  }

  patchRevisedPackage(id:number)
    : Observable<any>{
    return this.http.patch(`${this.url}/revised/${id}`,{})
  }

  patchDeliveryDate( id:number, date: string)
  :Observable<any>{
    return this.http.patch(`${this.url}/date/${id}`,date)
  }
}
