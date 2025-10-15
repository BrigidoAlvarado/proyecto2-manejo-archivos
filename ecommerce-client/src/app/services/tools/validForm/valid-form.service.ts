import { Injectable } from '@angular/core';
import {FormGroup} from "@angular/forms";

@Injectable({
  providedIn: 'root'
})
export class ValidFormService {

  constructor() { }

  isInvalid(formGroup: FormGroup, field: string): boolean {
    const control = formGroup.get(field);
    return !!(control && control.invalid && (control.touched || control.dirty));
  }
}
