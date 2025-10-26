import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {AppConfig} from "../../config/app.constants";
import {Observable} from "rxjs";
import {Notification} from "../../entities/notification";

@Injectable({
  providedIn: 'root'
})
export class NotificationService {

  private url: string = `${AppConfig.API_URL}/notification`;

  constructor(
    private http: HttpClient,
  ) { }

  public getNotificationsByUserId( id : number )
    :Observable<Notification[]> {
    return this.http.get<Notification[]>(`${this.url}/${id}`, {});
  }
}
