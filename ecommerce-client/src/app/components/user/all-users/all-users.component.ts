import {Component, OnInit} from '@angular/core';
import {User} from "../../../entities/user/user";
import {UserService} from "../../../services/user/user.service";
import {MessageService} from "../../../services/message.service";
import {DecimalPipe} from "@angular/common";
import {AppConfig} from "../../../config/app.constants";
import {RouterLink} from "@angular/router";

@Component({
  selector: 'app-all-users',
  standalone: true,
  imports: [
    DecimalPipe,
    RouterLink
  ],
  templateUrl: './all-users.component.html',
  styleUrl: './all-users.component.scss'
})
export class AllUsersComponent implements OnInit {

  users: User[] = []

  constructor(
    private _userService: UserService,
    private messageService: MessageService,
  ) {
  }

  ngOnInit() {
    this._userService.getAllUsers().subscribe({
      next: data => {
        this.users = data
      },
      error: err => {
        const msg = 'Error al cargar el listado de usuarios en la aplicacion'
        console.log(msg, err)
        this.messageService.error(msg)
      }
    })
  }

  protected readonly AppConfig = AppConfig;
}
