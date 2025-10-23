import { Injectable } from '@angular/core';
import {AppConfig} from "../../config/app.constants";
import {HttpClient} from "@angular/common/http";
import {SanctionProduct} from "../../entities/sanction/sanction-product";
import {Observable} from "rxjs";
import {SanctionDeliveryPackage} from "../../entities/sanction/sanction-delivery-package";

@Injectable({
  providedIn: 'root'
})
export class SanctionService {

  private url = `${AppConfig.API_URL}/sanction`;

  constructor(
    private http: HttpClient
  ) { }

  postSanctionProduct(sanction: SanctionProduct )
  :Observable<any>{
    return this.http.post<any>(this.url, sanction);
  }

  postSanctionDeliveryPackage( sanction: SanctionDeliveryPackage)
  :Observable<any>{
    return this.http.post<any>(`${this.url}/delivery-package`, sanction);
  }
}
