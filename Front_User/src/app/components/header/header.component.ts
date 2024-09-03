import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { WishlistComponent } from '../wishlist/wishlist.component'; // Adjust path as needed

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [CommonModule, RouterModule, WishlistComponent],
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent {
  isWishlistVisible: boolean = false;

  toggleWishlist() {
    this.isWishlistVisible = !this.isWishlistVisible;
  }
}
