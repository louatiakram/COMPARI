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
  products: Product[] = []; // Use the Product interface

  constructor(private productService: ProductService) {}

  ngOnInit(): void {
    this.productService.getAllProducts().subscribe((data: Product[]) => {
      console.log("results", data);
      this.products = data;
    });
  }

  ngAfterViewInit(): void {
    new Swiper('.product-swiper', {
      slidesPerView: 1, // Adjust this value based on your design
      spaceBetween: 10,
      pagination: {
        el: '.swiper-pagination',
        clickable: true, // Enable pagination dots if needed
      },
      navigation: false, // Disable navigation arrows
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
