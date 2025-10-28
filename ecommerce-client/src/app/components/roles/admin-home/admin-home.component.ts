import { Component } from '@angular/core';
import {AllUsersComponent} from "../../user/all-users/all-users.component";

@Component({
  selector: 'app-admin-home',
  standalone: true,
  imports: [
    AllUsersComponent
  ],
  templateUrl: './admin-home.component.html',
  styleUrl: './admin-home.component.scss'
})
export class AdminHomeComponent {

}
