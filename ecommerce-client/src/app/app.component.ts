import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import {HeaderComponent} from "./components/layout/header/header.component";
import {MessageComponent} from "./components/message/message.component";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, HeaderComponent, MessageComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  title = 'ecommerce-client';
}
