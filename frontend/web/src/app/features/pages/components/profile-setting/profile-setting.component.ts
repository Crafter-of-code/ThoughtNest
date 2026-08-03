import {
  Component,
  EventEmitter,
  Input,
  Output,
  WritableSignal,
  signal,
  OnInit,
} from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';

import { SolidButtonComponent } from '../../../../shared/components/solid-button/solid-button.component';
import { MainContainerComponent } from '../main-container/main-container.component';
import { GlobalInputComponent } from '../../../../shared/components/global-input/global-input.component';

import { UserService } from '../../../../core/services/user/user.service';
import { profileSettingDetailType } from './type';
import { NotificationService } from '../../../../core/services/notification/notification.service';

@Component({
  selector: 'app-profile-setting',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    SolidButtonComponent,
    MainContainerComponent,
    GlobalInputComponent,
  ],
  templateUrl: './profile-setting.component.html',
  styleUrl: './profile-setting.component.css',
})
export class ProfileSettingComponent implements OnInit {
  constructor(
    private userService: UserService,
    private notification: NotificationService
  ) {}

  @Output() showSettingComponent = new EventEmitter<boolean>();

  @Input() ownerData: profileSettingDetailType = {
    userName: '',
    userLocation: '',
    userBio: '',
    userTopic: [],
    userImageUrl: '',
  };

  userSingleTopic: WritableSignal<string> = signal('');

  userTopics: string[] = [];

  fileData: File | null = null;

  userUpdatedDataForm = new FormGroup({
    userName: new FormControl<string>(''),
    userLocation: new FormControl<string>(''),
    userBio: new FormControl<string>(''),
  });

  ngOnInit(): void {
    this.userUpdatedDataForm.patchValue({
      userName: this.ownerData.userName,
      userLocation: this.ownerData.userLocation,
      userBio: this.ownerData.userBio,
    });

    this.userTopics = [...this.ownerData.userTopic];
  }

  closeSettingComponent(): void {
    this.showSettingComponent.emit(false);
  }

  fileUploade(event: Event): void {
    const input = event.target as HTMLInputElement;

    if (!input.files || input.files.length === 0) {
      return;
    }

    this.fileData = input.files[0];
  }

  removeTopicHandler(index: number): void {
    this.userTopics.splice(index, 1);
  }

  onChange(event: Event): void {
    const input = event.target as HTMLInputElement;
    this.userSingleTopic.set(input.value);
  }

  pushToArray(): void {
    const topic = this.userSingleTopic().trim();

    if (!topic) {
      return;
    }

    if (!this.userTopics.includes(topic)) {
      this.userTopics.push(topic);
    }

    this.userSingleTopic.set('');
  }

  uploadSendToServer(): void {
    const values = this.userUpdatedDataForm.getRawValue();

    const formData: any = new FormData();

    formData.append('userName', values.userName ?? '');
    formData.append('userBio', values.userBio ?? '');
    formData.append('userLocation', values.userLocation ?? '');

    if (this.fileData) {
      formData.append('userProfileData', this.fileData, this.fileData.name);
    }

    // Send HashSet<String>
    this.userTopics.forEach((topic) => {
      formData.append('userTopic', topic);
    });

    // Debug
    console.log('----- FormData -----');
    for (const pair of formData.entries()) {
      console.log(pair[0], pair[1]);
    }

    this.userService.patchUpdatedDetail(formData).subscribe({
      next: (response) => {
        console.log('Profile updated successfully', response);
        this.notification.setNotification(response.status, response.message);
        this.closeSettingComponent();
      },
      error: (error) => {
        this.notification.setNotification(
          error.error.status,
          error.error.message
        );
      },
    });
  }
}
