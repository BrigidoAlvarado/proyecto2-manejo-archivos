import {Component, OnInit} from '@angular/core';
import {SanctionResponse} from "../../entities/sanction/sanction-response";
import {SanctionService} from "../../services/sanction/sanction.service";
import {DatePipe, Location} from "@angular/common";
import {ActivatedRoute} from "@angular/router";
import {MessageService} from "../../services/message.service";

@Component({
  selector: 'app-show-sanctions',
  standalone: true,
  imports: [
    DatePipe
  ],
  templateUrl: './show-sanctions.component.html',
  styleUrl: './show-sanctions.component.scss'
})
export class ShowSanctionsComponent implements OnInit {

  sanctions: SanctionResponse[] = []

  constructor(
    private sanctionService: SanctionService,
    private activatedRoute: ActivatedRoute,
    private messageService: MessageService,
    private location: Location
  ) {
  }

  ngOnInit() {
    const userId = this.activatedRoute.snapshot.params['id'];
    this.sanctionService.getSanctionsByUserId(userId).subscribe({
      next: data => {
        this.sanctions = data;
      },
      error: err => {
        const msg = 'Error al cargar las saciones del usuario por id'
        console.error(msg, err)
        this.messageService.error(msg)
      }
    })
  }

  protected goBack() {
    this.location.back();
  }
}
