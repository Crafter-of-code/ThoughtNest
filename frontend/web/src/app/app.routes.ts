import { Routes } from '@angular/router';

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
        path: 'setting',
        loadComponent: () =>
          import('./features/pages/setting-page/setting-page.component').then(
            (page) => page.SettingPageComponent
          ),
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
