import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {AppConfig} from "../../config/app.constants";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  private url: string = `${AppConfig.API_URL}/category`;

  constructor(
    private http: HttpClient,
  ) { }

  get(): Observable<string[]>{
    return this.http.get<string[]>(this.url);
  }
}
