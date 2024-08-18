import {ComponentFixture, TestBed} from '@angular/core/testing';

import {ComServiceComponent} from './com-service.component';

describe('ComServiceComponent', () => {
  let component: ComServiceComponent;
  let fixture: ComponentFixture<ComServiceComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [ComServiceComponent]
    });
    fixture = TestBed.createComponent(ComServiceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
