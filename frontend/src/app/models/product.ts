import { Category } from './category';

export interface Product {
  id: string | null;
  name: string;
  price: number;
  category: Category;
  stock: number;
  hasStock: boolean;
  status: Status;
}

enum Status {
  ENABLED,
  DISABLED,
}
