import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { RouterModule } from '@angular/router';
import { FooterComponent } from './layout/footer/footer.component';
import { HeaderComponent } from './layout/header/header.component';
import { ChartComponent } from './layout/chart/chart.component';
import { SidebarComponent } from './layout/sidebar/sidebar.component';
import { CardsComponent } from './layout/cards/cards.component';
import { ReportsComponent } from './layout/reports/reports.component';
import { RecentActivityComponent } from './layout/recent-activity/recent-activity.component';
import { TopLikedComponent } from './layout/top-liked/top-liked.component';
import { PageTitleComponent } from './layout/page-title/page-title.component';
import { TrafficReportComponent } from './layout/traffic-report/traffic-report.component';
@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    RouterOutlet,
    RouterModule,
    HeaderComponent,
    PageTitleComponent,
    ChartComponent,
    FooterComponent,
    SidebarComponent,
    CardsComponent,
    ReportsComponent,
    RecentActivityComponent,
    TopLikedComponent,
    TrafficReportComponent,
  ],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css',
})
export class AppComponent {
  title = 'FrontEnd';
}
