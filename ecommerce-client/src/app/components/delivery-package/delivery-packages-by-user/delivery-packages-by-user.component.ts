import {Component, OnInit} from '@angular/core';
import {DatePipe, DecimalPipe} from "@angular/common";
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {DeliveryPackage} from "../../../entities/delivery-package";
import {DeliveryPackageService} from "../../../services/delivery-package/delivery-package.service";
import {MessageService} from "../../../services/message.service";
import {SanctionService} from "../../../services/sanction/sanction.service";
import {ValidFormService} from "../../../services/tools/validForm/valid-form.service";
import {RouterLink} from "@angular/router";

@Component({
  selector: 'app-delivery-packages-by-user',
  standalone: true,
  imports: [
    DatePipe,
    DecimalPipe,
    ReactiveFormsModule,
    RouterLink
  ],
  templateUrl: './delivery-packages-by-user.component.html',
  styleUrl: './delivery-packages-by-user.component.scss'
})
export class DeliveryPackagesByUserComponent implements OnInit {

  deliveryPackages: DeliveryPackage[] = [];
  deliveryPackageSelected: DeliveryPackage | null = null;

  constructor(
    private deliveryPackageService: DeliveryPackageService,
    private messageService: MessageService,
    public validForm: ValidFormService,
  ) {
  }

  ngOnInit() {
   this.deliveryPackageService.getAllByUser().subscribe({
     next: data => {
       this.deliveryPackages = data;
     },
     error: err => {
       const msg = 'Error al cargar los paquetes de usuarios'
       console.error(msg, err);
       this.messageService.error(msg);
     }
   })
  }

  selectDeliveryPackage( deliveryPackage: DeliveryPackage):void{
    this.deliveryPackageSelected = deliveryPackage;
  }
}
