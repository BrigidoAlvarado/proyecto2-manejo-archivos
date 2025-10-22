import { Injectable } from '@angular/core';
import {AppConfig} from "../../config/app.constants";
import {HttpClient} from "@angular/common/http";
import {Sanction} from "../../entities/sanction";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class SanctionService {

  private url = `${AppConfig.API_URL}/sanction`;

  constructor(
    private http: HttpClient
  ) { }

  postSanction( sanction: Sanction )
  :Observable<any>{
    return this.http.post<any>(this.url, sanction);
  }
}
