import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BillboardComponent } from '../billboard/billboard.component';
import { SearchComponent } from '../search/search.component';
import { ProductsComponent } from '../products/products.component';
import { ComServiceComponent } from '../com-service/com-service.component';
import { YearlysaleComponent } from '../yearlysale/yearlysale.component';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule, BillboardComponent, SearchComponent, ProductsComponent, ComServiceComponent, YearlysaleComponent],
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent {

}
