import {Component, OnInit} from '@angular/core';
import {DeliveryPackage} from "../../../entities/delivery-package";
import {DatePipe} from "@angular/common";
import {DeliveryPackageService} from "../../../services/delivery-package/delivery-package.service";
import {MessageService} from "../../../services/message.service";
import {RouterLink} from "@angular/router";

@Component({
  selector: 'app-approve-delivery-package',
  standalone: true,
  imports: [
    DatePipe,
    RouterLink
  ],
  templateUrl: './approve-delivery-package.component.html',
  styleUrl: './approve-delivery-package.component.scss'
})
export class ApproveDeliveryPackageComponent implements OnInit {

  packages!: DeliveryPackage[];
  selectedDeliveryPackage: DeliveryPackage | null = null;

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
    this.deliveryPackageService.patchDeliverPackage(packageId).subscribe({
      next: () => {
        this.messageService.success('Entrega realizada exitosamente')
        this.ngOnInit()
      },
      error: err => {
        console.error('Error al entregar el paquete',err);
      }
    })
  }

  selectDeliveryPackage( deliveryPackage: DeliveryPackage):void{
    this.selectedDeliveryPackage = deliveryPackage;
  }

  updateDate( input: HTMLInputElement){

    if (!this.selectedDeliveryPackage) return

    if(input.value){
      const date =  input.value ;
      this.deliveryPackageService.patchDeliveryDate( this.selectedDeliveryPackage.id, date )
      .subscribe({
        next: () => {
          // reset
          input.value = '';
          this.selectedDeliveryPackage = null;
          this.messageService.success('Se actualizo la fecha de entrega exitosamente')
          this.ngOnInit()
        },
        error: err => {
          const msg = 'Error al actualizar la fecha de entrega'
          this.messageService.error(msg)
          console.error(msg, err)
        }
      })
    } else {
      this.messageService.error('Ingrese un fecha valida por favor')
    }
  }
}
