export interface Product {
  id: number;
  name: string;
  processor: string;
  processorRef: string;
  memory: string;
  hardDrive: string;
  gpu: string;
  gpuRef: string;
  screenSize: string;
  screenType: string;
  touchScreen: string;
  network: string;
  camera: string;
  warranty: string;
  refreshRate: string;
  color: string;
  price: number;
  description?: string;
  image?: string;
  storeProduct: StoreProduct[];
  ratings: Rating[];
  wishlists: Wishlist[];
  productsImgs: ProductsImgs[];
  imageUrl?: string; // Add this line to include imageUrl property
}

export interface StoreProduct {
  // Define fields as per backend if applicable
}

export interface Rating {
  // Define fields as per backend if applicable
}

export interface Wishlist {
  // Define fields as per backend if applicable
}

export interface ProductsImgs {
  id: number;
  imgName: string;
  // Add other fields if necessary
}
