import {Comment} from "../comment";

export interface ProductResponse {
  id: number;
  price: number;
  stock: number;
  isNew: boolean;
  new: boolean
  name: string;
  description: string;
  image: string;
  categories: string[];
  comments: Comment[];
}
