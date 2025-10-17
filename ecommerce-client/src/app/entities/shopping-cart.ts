import {PurchaseDetail} from "./purchase-detail";

export interface ShoppingCart {
  id: number;
  purchaseDetails: PurchaseDetail[];
}
