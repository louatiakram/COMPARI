import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Product } from 'src/app/modules/products/products.module';


@Component({
  selector: 'app-single-product',
  templateUrl: './single-product.component.html',
  styleUrls: ['./single-product.component.scss'],
  standalone: true,
  imports: [CommonModule],
})
export class SingleProductComponent {
  @Input() product!: Product;

  getFirstImageUrl(): string {
    if (this.product?.productsImgs && this.product.productsImgs.length > 0) {
      // Construct the URL using the product name and image file name
      const firstImageName = this.product.productsImgs[0].imgName;
      return `assets/img/${this.product.name}/${firstImageName}`;
    }
    // Fallback image URL if no images are available
    return 'assets/img/default.jpg';
  }
}
