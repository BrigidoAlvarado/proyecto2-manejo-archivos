import {Component, OnInit} from '@angular/core';
import {DecimalPipe} from "@angular/common";
import {FormBuilder, FormGroup, FormsModule, ReactiveFormsModule} from "@angular/forms";
import {User} from "../../../entities/user/user";
import {UserReport} from "../../../entities/user/user-report";
import {UserService} from "../../../services/user/user.service";
import {MessageService} from "../../../services/message.service";
import {ReportRequest} from "../../../entities/report-request";

@Component({
  selector: 'app-user-top-products-send',
  standalone: true,
    imports: [
        DecimalPipe,
        FormsModule,
        ReactiveFormsModule
    ],
  templateUrl: './user-top-products-send.component.html',
  styleUrl: './user-top-products-send.component.scss'
})
export class UserTopProductsSendComponent implements OnInit {

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

    this.userService.getTopByProductsSend(reportRequest).subscribe({
      next: data => {
        this.users = data},
      error: error =>{
        this.message.error('Error al cargar los datos');
        console.error('Error al cargar el reporte de clientes con mayor cantidad de procuctos vendidos',+error)
      },
    })
  }
}
