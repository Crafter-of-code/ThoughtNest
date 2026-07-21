import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UploadBlogComponent } from './upload-blog.component';

describe('UploadBlogComponent', () => {
  let component: UploadBlogComponent;
  let fixture: ComponentFixture<UploadBlogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [UploadBlogComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UploadBlogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
