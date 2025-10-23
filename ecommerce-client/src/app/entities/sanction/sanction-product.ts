export interface SanctionProduct {

  productId: number;
  reason: string;
  amountDays: number;
  approveProductRequest: {
    id: number;
    isApprove: boolean;
  }
}
