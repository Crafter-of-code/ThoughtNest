import { Component, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { GlobalInputComponent } from '../../../shared/components/global-input/global-input.component';
import {
  FormControl,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { NotificationService } from '../../../core/services/notification/notification.service';
import { UserService } from '../../../core/services/user/user.service';
import { Router, RouterOutlet } from '@angular/router';
import { CommonModule, Location } from '@angular/common';
type userDetailType = {
  publicId: string;
  userName: string;
  userProfileImage: string;
};
@Component({
  selector: 'app-members-page',
  imports: [
    GlobalInputComponent,
    ReactiveFormsModule,
    RouterOutlet,
    CommonModule,
  ],
  templateUrl: './members-page.component.html',
  styleUrl: './members-page.component.css',
})
export class MembersPageComponent implements OnInit, OnChanges {
  constructor(
    private notification: NotificationService,
    private userService: UserService,
    private nav: Router,
    private location: Location
  ) {}
  myPublicId: string = localStorage.getItem('publicId') ?? '';
  userDetail: userDetailType[] = [];
  defaultProfileImageUrl = '/user.png';
  ngOnInit(): void {}
  userInputData = new FormGroup({
    userName: new FormControl<string>('', {
      validators: [Validators.required],
    }),
  });
  searchUserName() {
    const userName = this.userInputData.value.userName;
    this.userDetail.length = 5;
    if (userName != '' && userName != null) {
      this.userService.getUserName(userName).subscribe({
        next: (response) => {
          this.userDetail = response.data;
          this.userInputData.getRawValue().userName = '';
        },
        error: (err) => {
          this.notification.setNotification(false, err.error.error);
        },
      });
    } else {
    }
  }
  ngOnChanges(changes: SimpleChanges): void {
    this.location.back();
  }
  searchCompeletDetailOfUser(id: string) {
    this.nav.navigate(['/members', id]);
  }
}
