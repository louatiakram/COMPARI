import {Component, OnInit} from '@angular/core';
import {CommonModule} from '@angular/common';
import {ProductService} from 'src/app/services/products.service';
import {SingleProductComponent} from '../single-product/single-product.component';
import {Product} from 'src/app/modules/products/products.module';

@Component({
  selector: 'app-products',
  standalone: true,
  imports: [CommonModule, SingleProductComponent],
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.scss'],
})
export class ProductsComponent implements OnInit {
  products: Product[] = []; // Use the Product interface

  constructor(private productService: ProductService) {
  }

  ngOnInit(): void {
    this.productService.getAllProducts().subscribe((data: Product[]) => {
      console.log("results", data)
      this.products = data;
    });
  }
}
