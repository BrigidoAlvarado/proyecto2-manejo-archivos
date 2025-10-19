import {Component, OnInit} from '@angular/core';
import {DeliveryPackage} from "../../entities/delivery-package";
import {DatePipe} from "@angular/common";
import {DeliveryPackageService} from "../../services/delivery-package/delivery-package.service";
import {MessageService} from "../../services/message.service";

@Component({
  selector: 'app-approve-delivery-package',
  standalone: true,
  imports: [
    DatePipe
  ],
  templateUrl: './approve-delivery-package.component.html',
  styleUrl: './approve-delivery-package.component.scss'
})
export class ApproveDeliveryPackageComponent implements OnInit {

  packages!: DeliveryPackage[];

  constructor(
    private deliveryPackageService: DeliveryPackageService,
    private messageService: MessageService,
  ) {
  }

  ngOnInit() {
    this.deliveryPackageService.getAllDeliveryPackagesInProgress().subscribe({
      next: data => {
        this.packages = data;
      },
      error: err => {
        console.log('Error al cargar los paquetes por aprobar',err);
      }
    })
  }

  deliver(packageId: number):void{
    this.deliveryPackageService.deliverPackage(packageId).subscribe({
      next: data => {
        this.messageService.success('Entrega realizada exitosamente')
        this.ngOnInit()
      },
      error: err => {
        console.error('Error al entregar el paquete',err);
      }
    })
  }
}
