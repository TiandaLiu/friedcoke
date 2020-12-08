import { Component, OnInit } from '@angular/core';
import { Router} from '@angular/router';
import {EmailService} from '../email.service';
import {Email} from '../email';
import {GlobalConstants} from '../common/global-constants';

@Component({
  selector: 'app-emails',
  templateUrl: './emails.component.html'
})
export class EmailsComponent implements OnInit {
  loginInfo: string;
  emails: Email[];

  constructor(private emailService: EmailService) { }

  ngOnInit(): void {
    this.loginInfo = GlobalConstants.username;
    this.getAllEmails();
  }

  getAllEmails(): void {
    this.emailService.getAllEmail().subscribe(emails => this.emails = emails.filter(email => email.receiver === this.loginInfo));
  }

}
