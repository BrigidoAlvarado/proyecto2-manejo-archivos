import {Component, OnInit} from '@angular/core';
import {Message} from "../../entities/message";
import {MessageService} from "../../services/message.service";

@Component({
  selector: 'app-message',
  standalone: true,
  imports: [],
  templateUrl: './message.component.html',
  styleUrl: './message.component.scss'
})
export class MessageComponent implements OnInit {

  messages: Message[] = [];

  constructor(private messageService: MessageService) {}

  ngOnInit() {
    this.messageService.getMessages$().subscribe(
      (message) => {
        this.messages.push(message);
        setTimeout(() => this.messages.shift(), 3000);
      }
    )
  }
}
