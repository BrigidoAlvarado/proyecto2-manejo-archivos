import { Injectable } from '@angular/core';
import { AppConfig } from "../../config/app.constants";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class CardService {

  private url:string = `${AppConfig.API_URL}/card/pay`;

  constructor(
    private http: HttpClient,
  ) { }


}
