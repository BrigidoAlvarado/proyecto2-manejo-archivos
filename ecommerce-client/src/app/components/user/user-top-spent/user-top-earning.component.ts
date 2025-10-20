import {Component, OnInit} from '@angular/core';
import {UserReport} from "../../../entities/user/user-report";
import {UserService} from "../../../services/user/user.service";
import {MessageService} from "../../../services/message.service";
import {DecimalPipe} from "@angular/common";
import {RouterLink} from "@angular/router";
import {FormBuilder, FormGroup, FormsModule, ReactiveFormsModule} from "@angular/forms";
import {ReportRequest} from "../../../entities/report-request";

@Component({
  selector: 'app-user-top-spent',
  standalone: true,
  imports: [
    DecimalPipe,
    RouterLink,
    FormsModule,
    ReactiveFormsModule
  ],
  templateUrl: './user-top-earning.component.html',
  styleUrl: './user-top-earning.component.scss'
})
export class UserTopEarningComponent implements OnInit {

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

    this.userService.getTopSpent(reportRequest).subscribe({
      next: data => {
        console.log('get success');
        this.users = data},
      error: error =>{
        this.message.error('Error al cargar los datos');
        console.error('Error al cargar el reporte de productos mas vendidos',+error)
      },
    })
  }
}
