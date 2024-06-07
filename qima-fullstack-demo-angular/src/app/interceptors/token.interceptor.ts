import { HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';
import { AuthService } from '../auth/service/auth.service';

export const tokenInterceptor: HttpInterceptorFn = (req, next) => {

  const authToken = inject(AuthService).getAccessToken;

  if(req.url.includes('/auth')) {
    return next(req);
  }
  const newReq = req.clone({
    headers: req.headers.append('Authorization', authToken),
  });
  return next(newReq);
};
