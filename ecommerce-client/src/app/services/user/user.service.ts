import { Injectable } from '@angular/core';
import {AppConfig} from "../../config/app.constants";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {UserReport} from "../../entities/user/user-report";
import {ReportRequest} from "../../entities/report-request";
import {User} from "../../entities/user/user";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private url: string = `${AppConfig.API_URL}/user`;

  constructor(
    private http: HttpClient
  ) { }

  getTopEarning(reportRequest: ReportRequest)
  :Observable<UserReport[]>{
    const params:any = []
    if (reportRequest.startDate) params.startDate = reportRequest.startDate;
    if (reportRequest.endDate) params.endDate = reportRequest.endDate;
    return this.http.get<UserReport[]>(`${this.url}/top/spent`, {params: params})
  }

  getTopByProductsSend(reportRequest: ReportRequest):
    Observable<UserReport[]>{
    const params:any = []
    if (reportRequest.startDate) params.startDate = reportRequest.startDate;
    if (reportRequest.endDate) params.endDate = reportRequest.endDate;
    return this.http.get<UserReport[]>(`${this.url}/top/products-send`, {params: params});
  }

  getTopByPackagesOrdered(reportRequest: ReportRequest):
    Observable<UserReport[]>{
    const params:any = []
    if (reportRequest.startDate) params.startDate = reportRequest.startDate;
    if (reportRequest.endDate) params.endDate = reportRequest.endDate;
    return this.http.get<UserReport[]>(`${this.url}/top/packages-ordered`, {params: params});
  }

  getTopByProductsApprove():
    Observable<UserReport[]>{

    return this.http.get<UserReport[]>(`${this.url}/top/products-approve`);
  }

  getAllUsers()
  :Observable<User[]>{
    return this.http.get<User[]>(`${this.url}/all`);
  }

  getUserById( id: number )
  :Observable<User>{
    return this.http.get<User>(`${this.url}/${id}`);
  }

  putUser( user: User )
  :Observable<any>{
    return this.http.put(this.url, user);
  }
}
