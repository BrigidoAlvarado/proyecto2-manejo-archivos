import { Component } from '@angular/core';
import {DecimalPipe} from "@angular/common";
import {FormBuilder, FormGroup, FormsModule, ReactiveFormsModule} from "@angular/forms";
import {UserReport} from "../../../entities/user/user-report";
import {UserService} from "../../../services/user/user.service";
import {MessageService} from "../../../services/message.service";
import {ReportRequest} from "../../../entities/report-request";
import {RouterLink} from "@angular/router";

@Component({
  selector: 'app-user-top-packages-ordered',
  standalone: true,
    imports: [
        DecimalPipe,
        FormsModule,
        ReactiveFormsModule,
      RouterLink
    ],
  templateUrl: './user-top-packages-ordered.component.html',
  styleUrl: './user-top-packages-ordered.component.scss'
})
export class UserTopPackagesOrderedComponent {

  users: UserReport[] = [];
  reportForm!: FormGroup;


  constructor(
    private userService: UserService,
    private message: MessageService,
    private formBuilder: FormBuilder,
  ) {
  }

  ngOnInit() {
    this.reportForm = this.formBuilder.group({
      startDate: [null],
      endDate: [null],
    })
    this.submit()
  }

  submit() {
    const reportRequest: ReportRequest = this.reportForm.value;
    console.log('submit', reportRequest);

    this.userService.getTopByPackagesOrdered(reportRequest).subscribe({
      next: data => {
        console.log('get success');
        this.users = data},
      error: error =>{
        this.message.error('Error al cargar los datos');
        console.error('Error al cargar el reporte de clientes con mas paquetes ordenados',+error)
      },
    })
  }
}
