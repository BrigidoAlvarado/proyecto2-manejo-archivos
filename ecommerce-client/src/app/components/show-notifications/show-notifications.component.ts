import {Component, Input, OnInit} from '@angular/core';
import {NotificationService} from "../../services/notification/notification.service";
import {MessageService} from "../../services/message.service";
import {Location} from "@angular/common";
import {Notification} from "../../entities/notification";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-show-notifications',
  standalone: true,
  imports: [],
  templateUrl: './show-notifications.component.html',
  styleUrl: './show-notifications.component.scss'
})
export class ShowNotificationsComponent implements OnInit {

  notifications: Notification[] = [];

  constructor(
    private notificationService: NotificationService,
    private messageService: MessageService,
    private location: Location,
    private activatedRoute: ActivatedRoute,
  ) {
  }

  ngOnInit() {
    const userId: number = Number ( this.activatedRoute.snapshot.paramMap.get('id'));
    this.notificationService.getNotificationsByUserId( userId ).subscribe({
      next: result => {
        this.notifications = result
      },
      error: err => {
        const msg = 'Error al cargar las notificaciones del usuario'
        console.error(msg, err);
        this.messageService.error(msg);
      }
    })
  }

  goBack() {
   this.location.back();
  }
}
