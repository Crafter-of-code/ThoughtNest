import { Component, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { ActivatedRoute, Route, Router } from '@angular/router';
import { UserService } from '../../../core/services/user/user.service';
import { CommonModule, DatePipe } from '@angular/common';
import {
  getOwnerDetailResponseType,
  getUserDetailResponseType,
} from '../../../types/UserTypes';
import { NotificationService } from '../../../core/services/notification/notification.service';
import { SolidButtonComponent } from '../../../shared/components/solid-button/solid-button.component';
import { OutlineButtonComponent } from '../../../shared/components/outline-button/outline-button.component';
import { finalize, findIndex } from 'rxjs';
@Component({
  selector: 'app-public-profile',
  imports: [CommonModule, SolidButtonComponent, OutlineButtonComponent],
  templateUrl: './public-profile.component.html',
  styleUrl: './public-profile.component.css',
})
export class PublicProfileComponent implements OnInit, OnChanges {
  constructor(
    private router: ActivatedRoute,
    private userService: UserService,
    private notificatio: NotificationService,
    private nav: Router
  ) {}
  myPublicId: string = localStorage.getItem('publicId') ?? '';
  completeUserData: getUserDetailResponseType | null = null;
  following: boolean = false;
  ngOnChanges(changes: SimpleChanges): void {}
  ngOnInit(): void {
    this.router.paramMap.subscribe((params) => {
      const id = params.get('id');
      if (id) {
        this.userService.getCompleteUserDetail(id).subscribe({
          next: (respsonse) => {
            this.following = respsonse.data?.following ?? false;
            this.completeUserData = respsonse.data;
          },
          error: (error) => {
            console.log(error.error);
            this.notificatio.setNotification(
              false,
              'we are facing some data whiel gathering user detail'
            );
            this.nav.navigate(['/members']);
          },
        });
      }
    });
  }
  followHandler(id: string) {
    if (id != '') {
      this.userService.followUser(id).subscribe({
        next: (response: { status: boolean; message: string }) => {
          console.log(response);
          this.notificatio.setNotification(response.status, response.message);
          this.following = true;
        },
        error: (err) => {
          console.log(err);
        },
      });
    }
  }
  unfollowHandler(id: string) {
    if (id == '') return;
    this.userService.unfollowUser(id).subscribe({
      next: (response) => {
        console.log(response);
        this.following = false;
      },
      error: (err) => {
        console.log(err);
      },
    });
  }
}
