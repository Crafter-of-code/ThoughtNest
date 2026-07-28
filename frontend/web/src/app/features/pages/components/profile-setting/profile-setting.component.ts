import {
  Component,
  EventEmitter,
  Input,
  OnChanges,
  OnInit,
  Output,
  signal,
  Signal,
  SimpleChanges,
  WritableSignal,
} from '@angular/core';
import { SolidButtonComponent } from '../../../../shared/components/solid-button/solid-button.component';
import { MainContainerComponent } from '../main-container/main-container.component';
import { profileSettingDetailType } from './type';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { GlobalInputComponent } from '../../../../shared/components/global-input/global-input.component';

@Component({
  selector: 'app-profile-setting',
  imports: [
    SolidButtonComponent,
    MainContainerComponent,
    SolidButtonComponent,
    GlobalInputComponent,
    ReactiveFormsModule,
  ],
  templateUrl: './profile-setting.component.html',
  styleUrl: './profile-setting.component.css',
  standalone: true,
})
export class ProfileSettingComponent {
  @Output() showSettingComponent = new EventEmitter<boolean>();
  @Input() ownerData: profileSettingDetailType | any = {
    userName: '',
    userLocation: '',
    userProfileUrl: '',
    userBio: '',
    userTopic: [],
  };
  userSingleTopic: WritableSignal<string> = signal<string>('');
  userTopics: string[] = ['first', 'second'];
  userUpdatedDataForm = new FormGroup({
    userName: new FormControl<string>(''),
    userLocation: new FormControl<string>(''),
    userBio: new FormControl<string>(''),
  });
  fileData: File | null = null;
  closeSettingComponent(event: Event) {
    this.showSettingComponent.emit(false);
  }
  fileUploade(event: Event) {
    const input = event.target as HTMLInputElement;
    if (!input.files?.length) return;
    const file = input.files[0];
    this.fileData = file;
  }
  /**
   */
  removeTopicHandler(index: number) {
    this.userTopics.splice(index, 1);
  }
  /**/
  onChange(event: Event) {
    const input = event.target as HTMLInputElement;
    this.userSingleTopic.set(input.value);
  }
  /**/
  pushToArray() {
    if (this.userSingleTopic() != '') {
      this.userTopics.push(this.userSingleTopic());
    }
    this.userSingleTopic.set('');
  }
  uploadSendToServer() {
    console.log(this.userUpdatedDataForm.getRawValue().userLocation);
    const payLoad: profileSettingDetailType = {
      userName: this.userUpdatedDataForm.getRawValue().userName ?? '',
      userBio: this.userUpdatedDataForm.getRawValue().userBio ?? '',
      userLocation: this.userUpdatedDataForm.getRawValue().userLocation ?? '',
      userProfileUrl: this.fileData,
      userTopic: this.userTopics,
    };
    console.log(payLoad);
  }
}
