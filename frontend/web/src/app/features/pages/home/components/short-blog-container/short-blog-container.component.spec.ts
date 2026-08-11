import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShortBlogContainerComponent } from './short-blog-container.component';

describe('ShortBlogContainerComponent', () => {
  let component: ShortBlogContainerComponent;
  let fixture: ComponentFixture<ShortBlogContainerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ShortBlogContainerComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ShortBlogContainerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
