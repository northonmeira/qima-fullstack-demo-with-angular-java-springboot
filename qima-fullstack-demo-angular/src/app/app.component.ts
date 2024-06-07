import { Component, OnInit } from '@angular/core';
import { AuthService } from './auth/service/auth.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit {
  title = 'qima-fullstack-demo-angular1';

  constructor(private authService: AuthService, private router: Router) {

  }
  ngOnInit(): void {

  }

  logout() {
    this.authService.logout();
  }

  home() {
    this.router.navigate(['']);
  }

}
