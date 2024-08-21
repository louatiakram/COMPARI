import {Component} from '@angular/core';
import {CardBodyComponent, CardComponent, CardHeaderComponent, TextColorDirective} from '@coreui/angular';

@Component({
  templateUrl: 'typography.component.html',
  standalone: true,
  imports: [
    TextColorDirective,
    CardComponent,
    CardHeaderComponent,
    CardBodyComponent,
  ],
})
export class TypographyComponent {
  constructor() {
  }
}
