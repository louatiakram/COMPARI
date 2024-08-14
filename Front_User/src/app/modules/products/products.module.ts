// src/app/models/product.model.ts
export interface Product {
  productID: number;
  name: string;
  description: string;
  image: string;
  storeProduct: StoreProduct[];
  ratings: Rating[];
}

export interface StoreProduct {
}

export interface Rating {
}
