import { Component } from '@angular/core';
import {RouterLink} from "@angular/router";
import {AuthService} from "../../../services/auth/auth.service";
import {appConfig} from "../../../app.config";
import {AppConfig} from "../../../config/app.constants";

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [
    RouterLink
  ],
  templateUrl: './header.component.html',
  styleUrl: './header.component.scss'
})
export class HeaderComponent {

  constructor(public auth: AuthService) {
  }

  protected readonly AppConfig = AppConfig;
}
