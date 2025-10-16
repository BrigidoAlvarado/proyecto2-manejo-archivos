export interface ProductResponse {
  id: number;
  price: number;
  stock: number;
  isNew: boolean;
  name: string;
  description: string;
  image: string;
  categories: string[];
}
