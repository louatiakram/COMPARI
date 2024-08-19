export interface Product {
  id: number;                // Changed from productID
  name: string;
  processor: string;         // Added
  processorRef: string;      // Added
  memory: string;            // Added
  hardDrive: string;         // Added
  gpu: string;               // Added
  gpuRef: string;            // Added
  screenSize: string;        // Added
  screenType: string;        // Added
  touchScreen: string;       // Added
  network: string;           // Added
  camera: string;            // Added
  warranty: string;          // Added
  refreshRate: string;       // Added
  color: string;             // Added
  price: number;             // Added
  description: string;
  image: string;
  storeProduct: StoreProduct[];
  ratings: Rating[];
  wishlists: Wishlist[];     // Added
}

export interface StoreProduct {
  // Define fields as per backend
}

export interface Rating {
  // Define fields as per backend
}

export interface Wishlist {
  // Define fields as per backend
}
