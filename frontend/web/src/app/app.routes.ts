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
          import('./features/pages/home-page/home-page.component').then(
            (page) => page.HomePageComponent
          ),
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
