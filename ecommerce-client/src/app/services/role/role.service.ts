import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {AppConfig} from "../../config/app.constants";

@Injectable({
  providedIn: 'root'
})
export class RoleService {

  private baseUrl:string = `${AppConfig.API_URL}/role`;

  constructor(
    private http: HttpClient
  ) { }

  getRole(): Observable<string[]>{
    return this.http.get<string[]>(this.baseUrl);
}

}
