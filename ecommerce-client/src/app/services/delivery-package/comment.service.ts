import { Injectable } from '@angular/core';
import {AppConfig} from "../../config/app.constants";
import {HttpClient} from "@angular/common/http";
import {Comment} from "../../entities/comment";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class CommentService {

  private url: string = `${AppConfig.API_URL}/comment`

  constructor(
    private http: HttpClient,
  ) { }

  postComment(comment: Comment) {
    return this.http.post(this.url, comment);
  }
}
