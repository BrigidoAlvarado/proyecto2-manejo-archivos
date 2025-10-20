import {User} from "./user/user";
import {ShoppingCart} from "./shopping-cart";

export interface DeliveryPackage {

  id: number;
  userName: String;
  deliveryDate: Date;
  departureDate: Date;
  shoppingCart: ShoppingCart;
  isDelivered: boolean;
}
