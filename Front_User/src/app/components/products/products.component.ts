import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SingleProductComponent } from '../single-product/single-product.component';

@Component({
  selector: 'app-products',
  standalone: true,
  imports: [CommonModule, SingleProductComponent],
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.scss']
})
export class ProductsComponent {

}
