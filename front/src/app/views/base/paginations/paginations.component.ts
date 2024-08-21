import {Component} from '@angular/core';
import {RouterLink} from '@angular/router';
import {DocsExampleComponent} from '@docs-components/public-api';
import {
  CardBodyComponent,
  CardComponent,
  CardHeaderComponent,
  ColComponent,
  PageItemComponent,
  PageLinkDirective,
  PaginationComponent,
  RowComponent,
  TextColorDirective
} from '@coreui/angular';

@Component({
  selector: 'app-paginations',
  templateUrl: './paginations.component.html',
  styleUrls: ['./paginations.component.scss'],
  standalone: true,
  imports: [RowComponent, ColComponent, TextColorDirective, CardComponent, CardHeaderComponent, CardBodyComponent, DocsExampleComponent, PaginationComponent, PageItemComponent, PageLinkDirective, RouterLink]
})
export class PaginationsComponent {

  constructor() {
  }

}
