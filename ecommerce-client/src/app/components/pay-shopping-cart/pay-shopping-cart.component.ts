import {Component, OnInit} from '@angular/core';
import {Card} from "../../entities/card";
import {ShoppingCartService} from "../../services/shopping-cart/shopping-cart.service";
import {Location} from "@angular/common";
import {FormBuilder, FormGroup, FormsModule, ReactiveFormsModule} from "@angular/forms";
import {ShoppingCart} from "../../entities/shopping-cart";
import {MessageService} from "../../services/message.service";
import {CardService} from "../../services/card/card.service";

@Component({
  selector: 'app-pay-shopping-cart',
  standalone: true,
  imports: [
    ReactiveFormsModule, FormsModule
  ],
  templateUrl: './pay-shopping-cart.component.html',
  styleUrl: './pay-shopping-cart.component.scss'
})
export class PayShoppingCartComponent implements OnInit {

  shoppingCart!: ShoppingCart;
  payForm!: FormGroup;

  creditsCards: Card[] = [
    {id: 1, cardNumber: '123'},
    {id: 2, cardNumber: '456'},
    {id: 3, cardNumber: '789'},
  ]

  constructor(
    private location: Location,
    private formBuilder: FormBuilder,
    private messageService: MessageService,
    private shoppingCartService: ShoppingCartService,
    private cardService: CardService,) {
  }

  ngOnInit() {
    this.shoppingCartService.getShoppingCart().subscribe({
      next: data =>  this.shoppingCart = data ,
      error: err => console.error('Error al cargar el carrito: '+err)
    })

    this.payForm = this.formBuilder.group({
      card: [''],
      saveCard: [false],
      selectedCard: [''],
    })
  }

  goBack(): void {
    this.location.back();
  }

  submit(): void {
    const numberCard:string = this.payForm.get('card')?.value
    const saveCard:boolean  = this.payForm.get('saveCard')?.value;
    const selectedCard:string = this.payForm.get('selectedCard')?.value

    let numberCardRequest: string = '';
    let saveCardRequest: boolean = false;

    if (!numberCard && !selectedCard) {
      this.messageService.error('No se ha ingresado niguna tarjeta de credito')
      return
    }

    if(numberCard){
      numberCardRequest = numberCard
      saveCardRequest = saveCard;
    }

    if(selectedCard){
      numberCardRequest = selectedCard
      saveCardRequest = false;
    }

    const confirm:boolean = window.confirm(`Se pagara   la cantidad de: Q${this.shoppingCart.total} con la tarjeta: ${numberCardRequest}`)

    if (confirm) {
      this.shoppingCartService.postPayment({cardNumber: numberCardRequest, save: saveCardRequest})
        .subscribe({
          next: () => {
            this.messageService.success('Pago exitoso')
            this.payForm.reset()
          },
          error: err => console.error('Error al pagar el carrito: '+err)
        })
    }
  }
}
