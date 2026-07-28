import { Component, OnInit } from '@angular/core';
import { GlassBackgroundComponent } from '../../../shared/components/glass-background/glass-background.component';
import { OutlineButtonComponent } from '../../../shared/components/outline-button/outline-button.component';
import { UserService } from '../../../core/services/user/user.service';
import { CommonModule, DatePipe } from '@angular/common';
import { BlogService } from '../../../core/services/blog/blog.service';
import { NotificationService } from '../../../core/services/notification/notification.service';
import { SmallButtonOneComponent } from '../components/small-button-one/small-button-one.component';
import { finalize } from 'rxjs';
import {
  OwnerDetailType,
  profileSettingDetailType,
  shortBlogDataType,
  shortBlogResponseDataType,
} from './type';
import { ProfileSettingComponent } from '../components/profile-setting/profile-setting.component';

@Component({
  selector: 'app-my-profile-page',
  imports: [
    GlassBackgroundComponent,
    OutlineButtonComponent,
    DatePipe,
    SmallButtonOneComponent,
    ProfileSettingComponent,
    CommonModule,
  ],
  templateUrl: './my-profile-page.component.html',
  styleUrl: './my-profile-page.component.css',
})
export class MyProfilePageComponent implements OnInit {
  constructor(
    private userService: UserService,
    private blogService: BlogService,
    private notification: NotificationService
  ) {}
  deletingBlogId: string | number | null = null;
  showProfileContainer: boolean = true;

  ownerDetail: OwnerDetailType = {
    userId: 0,
    userName: '',
    userEmail: '',
    publicId: '',
    noOfFollower: 0,
    noOfFollowing: 0,
    createdAt: new Date(),
    userProfile: {
      userPublished: 0,
      userBio: '',
      userImageUrl: '',
      userLocation: '',
      userProfileView: 0,
      userTotalLikes: 0,
    },
  };
  ownerShortBlogData: shortBlogDataType[] | any = [];
  profileSettingDetail: profileSettingDetailType = {
    userName: '',
    userBio: '',
    userLocation: '',
    userProfileUrl: '',
    userTopic: [],
  };
  ngOnInit(): void {
    this.userService.getOwnerCompleteDetail().subscribe({
      next: (response) => {
        this.ownerDetail = response.data;
        this.profileSettingDetail = {
          userName: response.data?.userName,
          userLocation: response.data.userProfile?.userLocation,
          userProfileUrl: response.data.userProfile?.userImageUrl,
          userBio: response.data.userProfile?.userBio,
          userTopic: [],
        };
      },
      error: (err) => {
        console.log(err.error);
      },
    });
    this.getMyShortBlogs();
  }
  getMyShortBlogs() {
    this.blogService.getOwner3Blog().subscribe({
      next: (response: shortBlogResponseDataType) => {
        this.ownerShortBlogData = response.data;
        console.log(response.data);
        console.log(Array.isArray(response.data));
      },
      error: (err) => {
        console.log(err.error);
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
          console.log(response);
          this.notification.setNotification(
            true,
            'You blog is successfully deleted'
          );
        },
        error: (err) => {
          console.log(err);
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
}
