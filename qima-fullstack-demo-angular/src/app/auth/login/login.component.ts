import { User } from './../model/user';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../service/auth.service';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent implements OnInit {

  form: FormGroup;

  ngOnInit(): void {

  }

  constructor(private formBuilder: FormBuilder,
    private service: AuthService,
    private snackBar: MatSnackBar
  ) {

    this.form = this.formBuilder.group({
      username: ['', [Validators.required]],
      password: ['', [Validators.required]]
    });
  }

  loggin() {
    var user = this.form.getRawValue() as User;
    if( this.isEmpty(user.username) ) {
      this.snackBar.open('', 'User required .', {
        duration: 3000
      });
      return;
    }

    if( this.isEmpty(user.password) ) {
      this.snackBar.open('', 'Password required .', {
        duration: 3000
      });
      return;
    }

    this.service.login(user).subscribe((response) => {
      if(!response.authenticated){
        this.snackBar.open('Fail to authenticate', 'User or password invalid .', {
          duration: 3000
        });
      }
    },
    () => {
      this.snackBar.open('Fail to authenticate', 'User or password invalid .', {
        duration: 3000
      });
    })
  }

  isEmpty(value: any) {
    return (value == null || (typeof value === "string" && value.trim().length === 0));
  }


}
