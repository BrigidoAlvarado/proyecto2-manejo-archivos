import { Component } from '@angular/core';
import {UserReport} from "../../../entities/user/user-report";
import {FormBuilder, FormGroup, FormsModule, ReactiveFormsModule} from "@angular/forms";
import {UserService} from "../../../services/user/user.service";
import {MessageService} from "../../../services/message.service";
import {ReportRequest} from "../../../entities/report-request";
import {DecimalPipe} from "@angular/common";
import {RouterLink} from "@angular/router";

@Component({
  selector: 'app-user-top-products-approve',
  standalone: true,
  imports: [
    DecimalPipe,
    FormsModule,
    ReactiveFormsModule,
    RouterLink
  ],
  templateUrl: './user-top-products-approve.component.html',
  styleUrl: './user-top-products-approve.component.scss'
})
export class UserTopProductsApproveComponent {


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
    this.userService.getTopByProductsApprove().subscribe({
      next: data => {
        console.log('get success');
        this.users = data},
      error: error =>{
        this.message.error('Error al cargar los datos');
        console.error('Error al cargar el reporte de usuarios con mas productos a la venta',+error)
      },
    })
  }
}
