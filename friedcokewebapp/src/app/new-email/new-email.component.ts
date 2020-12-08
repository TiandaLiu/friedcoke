import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {GlobalConstants} from '../common/global-constants';
import {EmailService} from '../email.service';

@Component({
  selector: 'app-new-email',
  templateUrl: './new-email.component.html'
})
export class NewEmailComponent implements OnInit {
  loginInfo: string;
  email: any;

  constructor(private emailService: EmailService,
              private router: Router) { }

  ngOnInit(): void {
    this.loginInfo = GlobalConstants.username;
    this.email = {
      sender: this.loginInfo,
      receiver: '',
      subject: '',
      content: '',
      timestamp: ''
    };
  }

  sendEmail(): void {
    this.emailService.sendEmail(this.email);
    this.router.navigate(['/success']);
  }
}
