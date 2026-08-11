import { Component, OnInit } from '@angular/core';
import { GlassBackgroundComponent } from '../../../shared/components/glass-background/glass-background.component';
import { OutlineButtonComponent } from '../../../shared/components/outline-button/outline-button.component';
import { UserService } from '../../../core/services/user/user.service';
import { CommonModule, DatePipe } from '@angular/common';
import { BlogService } from '../../../core/services/blog/blog.service';
import { NotificationService } from '../../../core/services/notification/notification.service';
import { SmallButtonOneComponent } from '../components/small-button-one/small-button-one.component';
import { finalize } from 'rxjs';
import { profileSettingDetailType, shortBlogDataType } from './type';
import { ProfileSettingComponent } from '../components/profile-setting/profile-setting.component';
import {
  shortBlogResponseType,
  universalResponseDataType,
} from '../../../types/BlogTypes';
import { getOwnerDetailResponseType } from '../../../types/UserTypes';
import { Router } from '@angular/router';
import { LoadingSpinnerComponent } from '../../../shared/components/loading-spinner/loading-spinner.component';

@Component({
  selector: 'app-my-profile-page',
  imports: [
    GlassBackgroundComponent,
    OutlineButtonComponent,
    DatePipe,
    SmallButtonOneComponent,
    ProfileSettingComponent,
    CommonModule,
    LoadingSpinnerComponent,
  ],
  templateUrl: './my-profile-page.component.html',
  styleUrl: './my-profile-page.component.css',
})
export class MyProfilePageComponent implements OnInit {
  constructor(
    private userService: UserService,
    private blogService: BlogService,
    private notification: NotificationService,
    private nav: Router
  ) {}
  loadingSpinner: boolean = true;
  deletingBlogId: string | number | null = null;
  showProfileContainer: boolean = false;

  ownerDetail: getOwnerDetailResponseType | null = null;
  ownerShortBlogData: shortBlogDataType[] | any = [];
  profileSettingDetail: profileSettingDetailType = {
    userName: '',
    userBio: '',
    userLocation: '',
    userImageUrl: '',
    userTopic: [],
  };
  ngOnInit(): void {
    this.userService
      .getOwnerCompleteDetail()
      .pipe(
        finalize(() => {
          this.loadingSpinner = false;
        })
      )
      .subscribe({
        next: (response) => {
          this.ownerDetail = response.data;
          this.profileSettingDetail = {
            userName: response.data?.userName ?? '',
            userLocation: response.data?.userProfile?.userLocation ?? '',
            userBio: response.data?.userProfile?.userBio ?? '',
            userImageUrl: response.data?.userProfile?.userImageUrl ?? '',
            userTopic: [],
          };
        },
        error: (err) => {
          this.notification.setNotification(false, 'Unable to find you detail');
        },
      });
    this.getMyShortBlogs();
  }
  getMyShortBlogs() {
    this.blogService.get3Blog().subscribe({
      next: (response: universalResponseDataType<shortBlogResponseType>) => {
        this.ownerShortBlogData = response.data;
      },
      error: (err) => {
        this.notification.setNotification(false, 'we unable to find you blog');
      },
    });
  }
  myBlogDeleteHandler(event: string | number) {
    this.deletingBlogId = event;
    this.blogService
      .deleteBlog(event.toString())
      .pipe(
        finalize(() => {
          this.getMyShortBlogs();
          this.deletingBlogId = null;
        })
      )
      .subscribe({
        next: (response) => {
          this.notification.setNotification(
            true,
            'You blog is successfully deleted'
          );
        },
        error: (err) => {
          this.notification.setNotification(
            false,
            'We are not able to delete your blog right now!'
          );
        },
      });
  }
  ReadyById(event: string | number) {
    console.log(event);
  }
  showProfileSetting(event: boolean) {
    this.showProfileContainer = event;
  }
  deleteAccount() {
    this.userService.deleteAccount().subscribe({
      next: (response: any) => {
        this.notification.setNotification(response.status, response.message);
        this.nav.navigate(['/login']);
      },

      error: (err) => {
        this.notification.setNotification(
          false,
          'We are unable to delete account right now'
        );
      },
    });
  }
}
