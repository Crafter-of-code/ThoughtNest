import { HttpInterceptorFn } from '@angular/common/http';

export const authInterceptor: HttpInterceptorFn = (req, next) => {
  const token = localStorage.getItem('token');
  const publicEndpoints = ['/api/auth/login', '/api/auth/signin'];
  const isPublic = publicEndpoints.some((endpoint) =>
    req.url.includes(endpoint)
  );

  if (token && !isPublic) {
    req = req.clone({
      setHeaders: {
        Authorization: `Bearer ${token}`,
      },
    });
  }
  return next(req);
};
