import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-single-product',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './single-product.component.html',
  styleUrls: ['./single-product.component.scss'],
})
export class SingleProductComponent {
  @Input() product: any = {}; // Accept product data from parent component

  constructor() {}

  ngOnInit(): void {}

  getImageUrl(folderName: string, imageName: string): string {
    // Return the URL of the image in the specified folder
    return `assets/img/${encodeURIComponent(folderName)}/${encodeURIComponent(imageName)}`;
  }

  getFirstImage(): { url: string, name: string } {
    // Check if the product has an images list and return the URL of the first image
    if (this.product.images && this.product.images.length > 0) {
      const firstImage = this.product.images[0]; // Get the first image name
      const imageUrl = this.getImageUrl(this.product.name, firstImage);
      return { url: imageUrl, name: firstImage };
    }
    // Return a placeholder image URL if no images are available
    return { url: 'assets/img/Apple MacBook Air M2/apple-macbook-pro-m2-8go-256-go-silver.jpg', name: 'placeholder-image.jpg' };
  }
}
