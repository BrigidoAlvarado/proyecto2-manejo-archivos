import {PurchaseDetail} from "./purchase-detail";

export interface ShoppingCart {
  id: number;
  total: number;
  purchaseDetails: PurchaseDetail[];
}
