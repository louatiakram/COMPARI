import {Component} from '@angular/core';
import {DocsExampleComponent} from '@docs-components/public-api';
import {
  AlignDirective,
  BorderDirective,
  CardBodyComponent,
  CardComponent,
  CardHeaderComponent,
  ColComponent,
  RowComponent,
  TableActiveDirective,
  TableColorDirective,
  TableDirective,
  TextColorDirective
} from '@coreui/angular';

@Component({
  selector: 'app-tables',
  templateUrl: './tables.component.html',
  styleUrls: ['./tables.component.scss'],
  standalone: true,
  imports: [RowComponent, ColComponent, TextColorDirective, CardComponent, CardHeaderComponent, CardBodyComponent, DocsExampleComponent, TableDirective, TableColorDirective, TableActiveDirective, BorderDirective, AlignDirective]
})
export class TablesComponent {

  constructor() {
  }

}
