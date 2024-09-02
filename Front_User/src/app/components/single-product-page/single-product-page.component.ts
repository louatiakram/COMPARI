import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ProductService } from 'src/app/services/products.service';
import { Product } from 'src/app/modules/products/products.module';
import { SingleProductComponent } from '../single-product/single-product.component';

@Component({
  selector: 'app-single-product-page',
  templateUrl: './single-product-page.component.html',
  styleUrls: ['./single-product-page.component.scss'],
  standalone: true,
  imports: [SingleProductComponent]
})
export class SingleProductPageComponent implements OnInit {
  product: Product | null = null;

  constructor(
    private route: ActivatedRoute,
    private productService: ProductService
  ) {}

  ngOnInit(): void {
    const productId = this.route.snapshot.paramMap.get('id');
    if (productId) {
      this.productService.getProductById(Number(productId)).subscribe((product: Product) => {
        this.product = product;
      });
    }
  }
}
