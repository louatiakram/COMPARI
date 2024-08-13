import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TrafficReportComponent } from './traffic-report.component';

describe('TrafficReportComponent', () => {
  let component: TrafficReportComponent;
  let fixture: ComponentFixture<TrafficReportComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TrafficReportComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TrafficReportComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
