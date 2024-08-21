import {Component} from '@angular/core';
import {DocsExampleComponent} from '@docs-components/public-api';
import {
  CardBodyComponent,
  CardComponent,
  CardHeaderComponent,
  ColComponent,
  FormControlDirective,
  FormLabelDirective,
  RowComponent,
  TextColorDirective
} from '@coreui/angular';

@Component({
  selector: 'app-ranges',
  templateUrl: './ranges.component.html',
  styleUrls: ['./ranges.component.scss'],
  standalone: true,
  imports: [RowComponent, ColComponent, TextColorDirective, CardComponent, CardHeaderComponent, CardBodyComponent, DocsExampleComponent, FormLabelDirective, FormControlDirective]
})
export class RangesComponent {

  constructor() {
  }

}
