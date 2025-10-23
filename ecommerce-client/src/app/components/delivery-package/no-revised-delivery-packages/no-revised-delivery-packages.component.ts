import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators} from "@angular/forms";
import {DeliveryPackage} from "../../../entities/delivery-package";
import {DeliveryPackageService} from "../../../services/delivery-package/delivery-package.service";
import {MessageService} from "../../../services/message.service";
import {ValidFormService} from "../../../services/tools/validForm/valid-form.service";
import {RouterLink} from "@angular/router";
import {DatePipe, DecimalPipe} from "@angular/common";
import {differenceInDays} from "date-fns";
import {SanctionDeliveryPackage} from "../../../entities/sanction/sanction-delivery-package";
import {SanctionService} from "../../../services/sanction/sanction.service";

@Component({
  selector: 'app-no-revised-delivery-packages',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    FormsModule,
    RouterLink,
    DatePipe,
    DecimalPipe
  ],
  templateUrl: './no-revised-delivery-packages.component.html',
  styleUrl: './no-revised-delivery-packages.component.scss'
})
export class NoRevisedDeliveryPackagesComponent implements OnInit {

  deliveryPackages: DeliveryPackage[] = [];
  deliveryPackageSelected: DeliveryPackage | null = null;
  sanctionForm!: FormGroup;

  constructor(
    private deliveryPackageService: DeliveryPackageService,
    private formBuilder: FormBuilder,
    private messageService: MessageService,
    private sanctionService: SanctionService,
    public validForm: ValidFormService,
  ) {
  }

  ngOnInit() {
    this.sanctionForm = this.formBuilder.nonNullable.group({
      reason: ['', [Validators.required],],
      amountDays: ['', [Validators.required, Validators.min(1)]],
    })
    this.getDeliveryPackages()
  }

  getDeliveryPackages(){
    this.deliveryPackageService.getAllPackagesNoRevised().subscribe({
      next: data => {
        this.deliveryPackages = data
      },
      error: err => {
        const msg = 'Error al cargar la lista de paquetes no revisados'
        console.error(msg,err)
        this.messageService.error(msg)
      }
    })
  }

  selectDeliveryPackage( deliveryPackage: DeliveryPackage ) {
    this.deliveryPackageSelected = deliveryPackage;
  }

  calculateDuration(deliveryPackage: DeliveryPackage): number {
    let endDate:Date;
    if (deliveryPackage.deliverAt){
      endDate = deliveryPackage.deliverAt;
    } else {
      endDate = new Date( Date.now() );
    }
    return Math.abs( differenceInDays( endDate, deliveryPackage.deliveryDate ) );
  }

  calBusinessDays(deliveryPackage: DeliveryPackage):number {
    return Math.abs( differenceInDays(deliveryPackage.departureDate, deliveryPackage.deliveryDate) )
  }

  isLate(deliveryPackage: DeliveryPackage):boolean {
    return this.calculateDuration( deliveryPackage ) > this.calBusinessDays(deliveryPackage);
  }

  sanction() {

    if (!this.deliveryPackageSelected) {
      this.messageService.error('No hay un paquete seleccionado')
      return
    }

    if (this.sanctionForm.valid){

      let sanction: SanctionDeliveryPackage = this.sanctionForm.value as SanctionDeliveryPackage;
      sanction.deliveryPackageId = this.deliveryPackageSelected!.id;

      this.messageService.info('procesando sancion por favor espere un momento...')

      this.sanctionService.postSanctionDeliveryPackage( sanction ).subscribe({
        next: () => {
          this.sanctionForm.reset()
          this.getDeliveryPackages()
          this.messageService.success('Se proceso la sancion correctamente')
        },
        error: err => {
          const msg = 'Error al sanciones a los usuarios vendedores del paquete seleccionado'
          console.error(msg,err)
          this.messageService.error(msg)
        }
      })

    } else {
      this.messageService.error('Formulario invalido por favor revise los campos')
    }
  }

  revised(id: number) {
    this.deliveryPackageService.patchRevisedPackage(id).subscribe({
      next: () => {
        this.getDeliveryPackages()
        this.messageService.success('Se marco como revisado el paquete exitosamente')
      },
      error: err => {
        const msg = 'Error al marcar el paquete como revisado'
        console.error(msg,err)
        this.messageService.error(msg)
      }
    })
  }
}
