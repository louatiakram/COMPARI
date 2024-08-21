import {Component} from '@angular/core';
import {ReactiveFormsModule} from '@angular/forms';
import {DocsExampleComponent} from '@docs-components/public-api';
import {
  CardBodyComponent,
  CardComponent,
  CardHeaderComponent,
  ColComponent,
  FormSelectDirective,
  RowComponent,
  TextColorDirective
} from '@coreui/angular';

@Component({
  selector: 'app-select',
  templateUrl: './select.component.html',
  styleUrls: ['./select.component.scss'],
  standalone: true,
  imports: [RowComponent, ColComponent, TextColorDirective, CardComponent, CardHeaderComponent, CardBodyComponent, DocsExampleComponent, FormSelectDirective, ReactiveFormsModule]
})
export class SelectComponent {

  constructor() {
  }

}
