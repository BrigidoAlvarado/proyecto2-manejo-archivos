import { Injectable } from '@angular/core';
import {BehaviorSubject, Observable, Subject} from "rxjs";
import {Message} from "../entities/message";

@Injectable({
  providedIn: 'root'
})
export class MessageService {

  private messageSubject = new Subject<Message>();
  constructor() { }

  getMessages$(): Observable<Message> {
    return this.messageSubject.asObservable();
  }

  success( message: string ) {
    this.messageSubject.next({type: 'success', message});
  }

  error( message: string ) {
    this.messageSubject.error({type: 'error', message});
  }

  info( message: string ) {
    this.messageSubject.next({type: 'info', message});
  }
}
