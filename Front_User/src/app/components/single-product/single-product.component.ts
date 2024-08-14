// src/app/components/single-product/single-product.component.ts
import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Product } from 'src/app/modules/products/products.module';

@Component({
  selector: 'app-single-product',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './single-product.component.html',
  styleUrls: ['./single-product.component.scss'],
})
export class SingleProductComponent {
  @Input() product: any={}; // Accept product data from parent component
  
  constructor() { }

  ngOnInit(): void {
  }
}
