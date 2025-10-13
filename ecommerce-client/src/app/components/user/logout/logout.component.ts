import {Component, OnInit} from '@angular/core';
import {AuthService} from "../../../services/auth/auth.service";
import {Router} from "@angular/router";
import {routes} from "../../../app.routes";
import {MessageService} from "../../../services/message.service";

@Component({
  selector: 'app-logout',
  standalone: true,
  imports: [],
  templateUrl: './logout.component.html',
  styleUrl: './logout.component.scss'
})
export class LogoutComponent implements OnInit {

  constructor(
    private auth: AuthService,
    private router: Router,
    private message: MessageService) {}

  ngOnInit() {
   this.auth.clearToken()
    this.message.info('Sesion Cerrada')
    this.router.navigate(['/'])
  }
}
