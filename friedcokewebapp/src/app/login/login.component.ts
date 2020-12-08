import { Component, OnInit } from '@angular/core';
import { Router} from '@angular/router';
import {LoginService} from '../login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html'
})
export class LoginComponent implements OnInit {
  username: string;
  password: string;

  constructor(private router: Router, private loginService: LoginService) { }

  ngOnInit(): void {
  }

  login(): void {
    // console.log(this.username);
    // console.log(this.password);
    if (this.username === 'admin' && this.password === 'admin') {
      this.router.navigate(['/admin']);
    }
    else if (this.loginService.verifyLogin(this.username, this.password) === 1) {
      this.router.navigate(['/']);
    }
    else {
      window.alert('invalid username/password, please try again.');
    }
  }
}
