import { Component } from '@angular/core';
import {
  ApproveDeliveryPackageComponent
} from "../../delivery-package/approve-delivery-package/approve-delivery-package.component";

@Component({
  selector: 'app-logistic-home',
  standalone: true,
  imports: [
    ApproveDeliveryPackageComponent
  ],
  templateUrl: './logistic-home.component.html',
  styleUrl: './logistic-home.component.scss'
})
export class LogisticHomeComponent {

}
