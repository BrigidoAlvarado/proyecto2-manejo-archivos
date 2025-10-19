import { Injectable } from '@angular/core';
import { AppConfig } from "../../config/app.constants";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import {Card} from "../../entities/card";

@Injectable({
  providedIn: 'root'
})
export class CardService {

  private url:string = `${AppConfig.API_URL}/card`;

  constructor(
    private http: HttpClient,
  ) { }

  getAllCards():
    Observable<Card[]>{
    return this.http.get<Card[]>(this.url)
  }
}
