import { Injectable } from '@angular/core';
import {APP_BASE_HREF} from "@angular/common";
import {AppConfig} from "../../config/app.constants";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {DeliveryPackage} from "../../entities/delivery-package";

@Injectable({
  providedIn: 'root'
})
export class DeliveryPackageService {

  private url:string = AppConfig.API_URL + '/delivery-package';

  constructor(
    private http: HttpClient,
  ) { }

  getAllDeliveryPackagesInProgress()
    :Observable<DeliveryPackage[]> {
    return this.http.get<DeliveryPackage[]>(this.url)
  }

  getAllPackagesNoRevised()
  :Observable<DeliveryPackage []>{
    return this.http.get<DeliveryPackage[]>(`${this.url}/no-revised`)
  }

  patchDeliverPackage(id:number):
  Observable<any>{
    return this.http.patch<DeliveryPackage>(`${this.url}/deliver/${id}`,{})
  }

  patchRevisedPackage(id:number)
    : Observable<any>{
    return this.http.patch(`${this.url}/revised/${id}`,{})
  }
}
