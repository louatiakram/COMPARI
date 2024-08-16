import { ComponentFixture, TestBed } from '@angular/core/testing';

import { YearlysaleComponent } from './yearlysale.component';

describe('YearlysaleComponent', () => {
  let component: YearlysaleComponent;
  let fixture: ComponentFixture<YearlysaleComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [YearlysaleComponent]
    });
    fixture = TestBed.createComponent(YearlysaleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
