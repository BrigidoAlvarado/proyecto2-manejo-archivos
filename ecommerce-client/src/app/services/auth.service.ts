import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import {Observable} from "rxjs";
import {User} from "../entities/user";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private apiURL:string = "http://localhost:8080/api/v1/auth"

  constructor(private http: HttpClient) { }

  login( credentials: { email: string, password: string } ): Observable<any>{
    return this.http.post(`${this.apiURL}/login`, credentials);
  }

  register( user: User): Observable<any>{
    return this.http.post(`${this.apiURL}/register`, user);
  }
}
