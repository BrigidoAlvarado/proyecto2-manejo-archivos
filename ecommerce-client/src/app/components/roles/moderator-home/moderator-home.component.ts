import { Component } from '@angular/core';
import {ApproveProductComponent} from "../../product/approve-product/approve-product.component";

@Component({
  selector: 'app-moderator-home',
  standalone: true,
  imports: [
    ApproveProductComponent
  ],
  templateUrl: './moderator-home.component.html',
  styleUrl: './moderator-home.component.scss'
})
export class ModeratorHomeComponent {

}
