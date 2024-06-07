import { CanActivateFn, Router } from '@angular/router';
import { AuthService } from '../auth/service/auth.service';
import { inject } from '@angular/core';

export const AuthGuard: CanActivateFn = (route, state) => {

  return inject(AuthService).authenticated ? true
  : inject(Router).createUrlTree(['/login']);

};
