import {ShoppingCart} from "./shopping-cart";

export interface DeliveryPackage {

  id: number;
  userName: String;
  userEmail: string;
  deliveryDate: Date;
  departureDate: Date;
  deliverAt: Date
  shoppingCart: ShoppingCart;
  isDelivered: boolean;
}
