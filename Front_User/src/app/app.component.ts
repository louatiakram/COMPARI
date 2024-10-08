import {Component} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterOutlet} from '@angular/router';
import {BillboardComponent} from './components/billboard/billboard.component';
import {HeaderComponent} from './components/header/header.component';
import {SearchComponent} from './components/search/search.component';
import {ProductsComponent} from './components/products/products.component';
import {FooterComponent} from './components/footer/footer.component';
import {ComServiceComponent} from './components/com-service/com-service.component';
import {YearlysaleComponent} from './components/yearlysale/yearlysale.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, RouterOutlet, BillboardComponent, HeaderComponent, SearchComponent, ProductsComponent, FooterComponent, ComServiceComponent, YearlysaleComponent],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'Front-User';
}
