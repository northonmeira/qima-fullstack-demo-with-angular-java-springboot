import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { User } from '../model/user';
import { Observable, tap } from 'rxjs';
import { AuthModel } from '../model/auth';


@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private basePath = 'http://localhost:8080/auth';

  constructor(private httpClient: HttpClient, private router: Router ) { }

  login(user: User): Observable<any> {
    return this.httpClient.post<AuthModel>(this.basePath + '/signin', user).pipe(
      tap((response) => {
        if(!response.authenticated) return;
        localStorage.setItem('accessToken', response.accessToken);
        localStorage.setItem('refreshToken', response.refreshToken);
        localStorage.setItem('user', response.username);
        localStorage.setItem('expiration', response.expiration);
        this.router.navigate(['']);
      })
    )
  }

  refreshToken() {
    this.httpClient.put<AuthModel>(this.basePath
      + '/refresh/' + localStorage.getItem('user'),
      { 'Authorization': localStorage.getItem('refreshToken') }).pipe(
        tap((response) => {
          localStorage.setItem('accessToken', response.accessToken);
          localStorage.setItem('refreshToken', response.refreshToken);
          localStorage.setItem('expiration', response.expiration);
        })
      );
  }

  logout() {
    localStorage.clear();
    this.router.navigate(['login']);
  }

  get authenticated(): boolean {
    return localStorage.getItem('accessToken') ? true : false;
  }

  get getAccessToken(): any {
    return 'Bearer ' + localStorage.getItem('accessToken');
  }

  get getRefreshToken(): any {
    return localStorage.getItem('refreshToken');
  }

  get getLoggedUser(): any {
    return localStorage.getItem('user');
  }

}
