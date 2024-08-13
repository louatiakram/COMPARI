import { Component } from '@angular/core';
import { ChartComponent } from '../chart/chart.component';
import { CardsComponent } from '../cards/cards.component';
import { SidebarComponent } from '../sidebar/sidebar.component';
import { ReportsComponent } from '../reports/reports.component';
import { RecentActivityComponent } from '../recent-activity/recent-activity.component';
import { TopLikedComponent } from '../top-liked/top-liked.component';
import { PageTitleComponent } from '../page-title/page-title.component';
import { TrafficReportComponent } from '../traffic-report/traffic-report.component';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [
    PageTitleComponent,
    ChartComponent,
    SidebarComponent,
    CardsComponent,
    ReportsComponent,
    RecentActivityComponent,
    TopLikedComponent,
    TrafficReportComponent,
  ],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {

}
