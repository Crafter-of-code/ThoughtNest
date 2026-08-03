import { Component, EventEmitter, Output } from '@angular/core';
import { GlobalInputComponent } from '../../../../shared/components/global-input/global-input.component';
import { SolidButtonComponent } from '../../../../shared/components/solid-button/solid-button.component';
import {
  FormControl,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { NotificationService } from '../../../../core/services/notification/notification.service';
import { BlogService } from '../../../../core/services/blog/blog.service';
import { MainContainerComponent } from '../main-container/main-container.component';

@Component({
  selector: 'app-upload-blog',
  imports: [
    GlobalInputComponent,
    SolidButtonComponent,
    ReactiveFormsModule,
    MainContainerComponent,
  ],
  templateUrl: './upload-blog.component.html',
  styleUrl: './upload-blog.component.css',
})
export class UploadBlogComponent {
  constructor(
    private notification: NotificationService,
    private blogService: BlogService
  ) {}
  @Output() showMainContainerOnScreen = new EventEmitter<Event>();
  imagePreview: string | null = null;
  FileSelected: File | null = null;
  buttonLoadingSpinner: boolean = false;
  buttonDisabled: boolean = false;
  uploadBlogInfo = new FormGroup({
    blogTitle: new FormControl<string>('', {
      validators: [Validators.required],
    }),
    blogContent: new FormControl<string>('', {
      validators: [Validators.required],
    }),
    coverImage: new FormControl<File | null>(null),
  });
  showBlogUploadContainer(event: Event) {
    this.showMainContainerOnScreen.emit(event);
  }
  onFileSelected(event: Event) {
    const input = event.target as HTMLInputElement;

    if (!input.files?.length) return;

    const file = input.files[0];
    this.FileSelected = file;
    this.uploadBlogInfo.patchValue({
      coverImage: file,
    });
    /*
    const reader = new FileReader();

    reader.onload = () => {
      this.imagePreview = reader.result as string;
    };

    reader.readAsDataURL(file);
    */
  }
  uploadBlogData() {
    this.buttonLoadingSpinner = true;
    this.buttonDisabled = true;
    const publicId = localStorage.getItem('publicId');
    const formData = new FormData();

    formData.append('blogTitle', this.uploadBlogInfo.value.blogTitle ?? '');

    formData.append('blogContent', this.uploadBlogInfo.value.blogContent ?? '');

    formData.append('coverImage', this.uploadBlogInfo.value.coverImage ?? '');
    formData.append('userPublicId', publicId ?? '');
    this.blogService.postBlog(formData).subscribe({
      next: (response) => {
        if (response.status) {
          this.notification.setNotification(response.status, response.message);
          this.uploadBlogInfo.reset();
          this.FileSelected = null;
          this.buttonLoadingSpinner = false;
          this.buttonDisabled = false;
        } else {
          this.notification.setNotification(response.status, response.message);
          this.buttonLoadingSpinner = false;
          this.buttonDisabled = false;
        }
      },
      error: (err) => {
        console.log(err);
        this.notification.setNotification(
          false,
          'We are facing some error while uploading you blog'
        );
        this.buttonLoadingSpinner = false;
        this.buttonDisabled = false;
      },
    });
  }
}
