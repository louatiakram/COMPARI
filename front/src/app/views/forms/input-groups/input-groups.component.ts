import {Component} from '@angular/core';
import {ReactiveFormsModule} from '@angular/forms';
import {RouterLink} from '@angular/router';
import {DocsExampleComponent} from '@docs-components/public-api';
import {
  ButtonDirective,
  CardBodyComponent,
  CardComponent,
  CardHeaderComponent,
  ColComponent,
  DropdownComponent,
  DropdownDividerDirective,
  DropdownItemDirective,
  DropdownMenuDirective,
  DropdownToggleDirective,
  FormCheckInputDirective,
  FormControlDirective,
  FormLabelDirective,
  FormSelectDirective,
  InputGroupComponent,
  InputGroupTextDirective,
  RowComponent,
  TextColorDirective,
  ThemeDirective
} from '@coreui/angular';

@Component({
  selector: 'app-input-groups',
  templateUrl: './input-groups.component.html',
  styleUrls: ['./input-groups.component.scss'],
  standalone: true,
  imports: [RowComponent, ColComponent, TextColorDirective, CardComponent, CardHeaderComponent, CardBodyComponent, DocsExampleComponent, InputGroupComponent, InputGroupTextDirective, FormControlDirective, FormLabelDirective, FormCheckInputDirective, ButtonDirective, ThemeDirective, DropdownComponent, DropdownToggleDirective, DropdownMenuDirective, DropdownItemDirective, RouterLink, DropdownDividerDirective, FormSelectDirective, ReactiveFormsModule]
})
export class InputGroupsComponent {

  constructor() {
  }

}
