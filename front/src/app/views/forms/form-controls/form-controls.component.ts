import {Component} from '@angular/core';
import {NgStyle} from '@angular/common';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {DocsExampleComponent} from '@docs-components/public-api';
import {
  ButtonDirective,
  CardBodyComponent,
  CardComponent,
  CardHeaderComponent,
  ColComponent,
  FormControlDirective,
  FormDirective,
  FormLabelDirective,
  RowComponent,
  TextColorDirective
} from '@coreui/angular';

@Component({
  selector: 'app-form-controls',
  templateUrl: './form-controls.component.html',
  styleUrls: ['./form-controls.component.scss'],
  standalone: true,
  imports: [RowComponent, ColComponent, TextColorDirective, CardComponent, CardHeaderComponent, CardBodyComponent, DocsExampleComponent, ReactiveFormsModule, FormsModule, FormDirective, FormLabelDirective, FormControlDirective, ButtonDirective, NgStyle]
})
export class FormControlsComponent {

  public favoriteColor = '#26ab3c';

  constructor() {
  }

}
