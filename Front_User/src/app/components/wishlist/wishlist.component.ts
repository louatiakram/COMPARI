import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-wishlist',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './wishlist.component.html',
  styleUrls: ['./wishlist.component.scss']
})
export class WishlistComponent {
  wishlistItems: string[] = []; // Replace with actual item type if needed

  constructor() {
    // Initialize or fetch wishlist items here
    this.loadWishlist();
  }

  loadWishlist() {
    // Example method to load wishlist items (could be from a service)
    this.wishlistItems = ['Item 1', 'Item 2', 'Item 3']; // Example items
  }

  removeItem(item: string) {
    this.wishlistItems = this.wishlistItems.filter(i => i !== item);
  }

  clearWishlist() {
    this.wishlistItems = [];
  }
}
