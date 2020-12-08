import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { HttpHeaders } from '@angular/common/http';

import {HttpClient} from '@angular/common/http';
import {GlobalConstants} from './common/global-constants';
import {Email} from './email';

@Injectable({ providedIn: 'root' })
export class EmailService {
  baseUrl = GlobalConstants.apiURL;

  constructor(private http: HttpClient) { }

  sendEmail(email: Email): void {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':  'application/json'
      })
    };
    const url = this.baseUrl + '/api/notification';
    const body = JSON.stringify(email);
    this.http.post(url, body, httpOptions).subscribe();
  }

  getAllEmail(): Observable<any> {
    return this.http.get(this.baseUrl + '/api/notification');
  }

}
