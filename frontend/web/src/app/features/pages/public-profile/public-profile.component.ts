import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Route } from '@angular/router';
import { UserService } from '../../../core/services/user/user.service';
import { CommonModule } from '@angular/common';
type completeUserDetailType = {
  publicId: string;
  userName: string;
  noOfFollowing: number;
  noOfFollower: number;
  noOfBlog: number;
  createAt: Date;
  userProfile: {
    userBio: string;
    userLocation: string;
    userImageUrl: string;
    userTotalLike: number;
    userProfileView: number;
    userPublished: number;
  };
};
@Component({
  selector: 'app-public-profile',
  imports: [CommonModule],
  templateUrl: './public-profile.component.html',
  styleUrl: './public-profile.component.css',
})
export class PublicProfileComponent implements OnInit {
  constructor(
    private router: ActivatedRoute,
    private userService: UserService
  ) {}
  completeUserData: completeUserDetailType | any = {};
  ngOnInit(): void {
    console.log(this.completeUserData);
    const id = this.router.snapshot.paramMap.get('id');
    if (id) {
      this.userService.getCompleteUserDetail(id).subscribe({
        next: (repsonse) => {
          console.log(repsonse.data);
          this.completeUserData = repsonse.data;
        },
        error: (error) => {
          console.log(error.error);
        },
      });
    }
    console.log(this.completeUserData);
  }
}
