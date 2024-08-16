import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TopLikedComponent } from './top-liked.component';

describe('TopLikedComponent', () => {
  let component: TopLikedComponent;
  let fixture: ComponentFixture<TopLikedComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TopLikedComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TopLikedComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
