import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HomeSkeletonUiComponent } from './home-skeleton-ui.component';

describe('HomeSkeletonUiComponent', () => {
  let component: HomeSkeletonUiComponent;
  let fixture: ComponentFixture<HomeSkeletonUiComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HomeSkeletonUiComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(HomeSkeletonUiComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
