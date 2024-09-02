import { Component, OnInit, AfterViewInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ProductService } from 'src/app/services/products.service';
import { SingleProductComponent } from '../single-product/single-product.component';
import { Product } from 'src/app/modules/products/products.module';
import Swiper from 'swiper';

@Component({
  selector: 'app-products',
  standalone: true,
  imports: [CommonModule, SingleProductComponent],
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.scss'],
})
export class ProductsComponent implements OnInit, AfterViewInit {
  products: Product[] = [];

  constructor(private productService: ProductService) {}

  ngOnInit(): void {
    this.productService.getAllProducts().subscribe((data: Product[]) => {
      this.products = data;
      console.log('All Products:', this.products); // Check if products are being loaded correctly
        setTimeout(() => this.initializeSwiper(), 0); // Initialize Swiper after data is loaded
    });
  }
  

  getTop5HigherPricedProducts(): Product[] {
    const filtered = this.products.sort((a, b) => b.price - a.price).slice(0, 5);
    console.log('getTop5HigherPricedProducts:', filtered); // Check if products are being filtered correctly
    return filtered;
  }

  getAppleMacbookProducts(): Product[] {
    const filtered = this.products.filter(product => product.name.toLowerCase().includes('apple macbook')).slice(0, 5);
    console.log('Apple MacBook Products:', filtered); // Check if products are being filtered correctly
    return filtered;
  }
  
  getProductsInRange2500To3000(): Product[] {
    const filtered = this.products.filter(product => product.price >= 2500 && product.price <= 3000).slice(0, 5);
    console.log('Products in Range 2500-3000:', filtered); // Check if products are being filtered correctly
    return filtered;
  }
  
  getProductsInRange2000To2500(): Product[] {
    const filtered = this.products.filter(product => product.price >= 2000 && product.price <= 2500).slice(0, 5);
    console.log('Products in Range 2000-2500:', filtered); // Check if products are being filtered correctly
    return filtered;
  }
  

  ngAfterViewInit(): void {
    this.initializeSwiper();
  }

  initializeSwiper(): void {
    new Swiper('.product-swiper', {
      slidesPerView: 1,
      spaceBetween: 10, // Adjust spacing between slides as needed
      pagination: {
        el: '.swiper-pagination',
        clickable: true,
      },
      mousewheel: true,
      keyboard: true,
      breakpoints: {
        640: {
          slidesPerView: 2,
          spaceBetween: 20,
        },
        768: {
          slidesPerView: 3,
          spaceBetween: 30,
        },
        1024: {
          slidesPerView: 4,
          spaceBetween: 40,
        },
      },
    });
  }
}
