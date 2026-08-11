import { Routes } from '@angular/router';
import { MyProfilePageComponent } from './features/pages/my-profile-page/my-profile-page.component';

export const routes: Routes = [
  {
    path: '',
    loadComponent: () =>
      import(
        './features/welcome/pages/welcome-page/welcome-page.component'
      ).then((page) => page.WelcomePageComponent),
  },
  {
    path: '',
    loadComponent: () =>
      import('./features/pages/layout-page/layout-page.component').then(
        (page) => page.LayoutPageComponent
      ),
    children: [
      {
        path: 'home',
        loadComponent: () =>
          import(
            './features/pages/home/home-layout-page/home-layout-page.component'
          ).then((page) => page.HomeLayoutPageComponent),
        children: [
          {
            path: '',
            loadComponent: () =>
              import(
                './features/pages/home/pages/home-page/home-page.component'
              ).then((page) => page.HomePageComponent),
          },
          {
            path: 'trending',
            loadComponent: () =>
              import(
                './features/pages/home/pages/trending-page/trending-page.component'
              ).then((page) => page.TrendingPageComponent),
          },
          {
            path: 'following',
            loadComponent: () =>
              import(
                './features/pages/home/pages/following-page/following-page.component'
              ).then((page) => page.FollowingPageComponent),
          },
        ],
      },
      {
        path: 'members',
        loadComponent: () =>
          import('./features/pages/members-page/members-page.component').then(
            (page) => page.MembersPageComponent
          ),
        children: [
          {
            path: ':id',
            loadComponent: () =>
              import(
                './features/pages/public-profile/public-profile.component'
              ).then((page) => page.PublicProfileComponent),
          },
        ],
      },
      {
        path: 'myprofile',
        component: MyProfilePageComponent,
      },
    ],
  },

  {
    path: 'signin',
    loadComponent: () =>
      import('./features/auth/pages/signin-page/signin-page.component').then(
        (page) => page.SigninPageComponent
      ),
  },
  {
    path: 'login',
    loadComponent: () =>
      import('./features/auth/pages/login-page/login-page.component').then(
        (page) => page.LoginPageComponent
      ),
  },
  // {
  //   path: 'home',
  //   loadComponent: () =>
  //     import('./features/pages/home-page/home-page.component').then(
  //       (page) => page.HomePageComponent
  //     ),
  // },
  {
    path: '**',
    loadComponent: () =>
      import('./features/not-found-page/not-found-page.component').then(
        (page) => page.NotFoundPageComponent
      ),
  },
];
