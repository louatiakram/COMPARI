import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BillboardComponent } from '../billboard/billboard.component';
import { SearchComponent } from '../search/search.component';
import { ProductsComponent } from '../products/products.component';
import { ComServiceComponent } from '../com-service/com-service.component';
import { YearlysaleComponent } from '../yearlysale/yearlysale.component';
import { SingleProductComponent } from '../single-product/single-product.component';

import { HttpClient, HttpClientModule } from '@angular/common/http';
@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule, BillboardComponent, SearchComponent, ProductsComponent, ComServiceComponent, YearlysaleComponent, SingleProductComponent, HttpClientModule],
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent {
}
