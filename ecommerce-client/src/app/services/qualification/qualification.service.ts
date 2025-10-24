import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {AppConfig} from "../../config/app.constants";
import {Qualification} from "../../entities/qualification";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class QualificationService {

  private url: string = `${AppConfig.API_URL}/qualification`;

  constructor(
    private http: HttpClient,
  ) { }

  postQualification( qualification: Qualification ):Observable<any> {
    return this.http.post(this.url, qualification);
  }
}
