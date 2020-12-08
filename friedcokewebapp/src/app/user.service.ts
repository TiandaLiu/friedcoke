import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { HttpHeaders } from '@angular/common/http';

import {HttpClient} from '@angular/common/http';
import {GlobalConstants} from './common/global-constants';

@Injectable({ providedIn: 'root' })
export class UserService {
  baseUrl = GlobalConstants.apiURL;

  constructor(private http: HttpClient) { }

  getWatchlist(userId: string): Observable<any> {
    return this.http.get(this.baseUrl + `/api/user/${userId}/watchlist`);
  }

  addAuctionToWatchlist(userId: string, auctionId: string): void {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':  'application/json'
      })
    };
    const url = this.baseUrl + `/api/user/${userId}/watchlist/${auctionId}`;
    const body = JSON.stringify({});
    this.http.post(url, body, httpOptions).subscribe();
  }

  getCart(userId: string): Observable<any> {
    return this.http.get(this.baseUrl + `/api/user/${userId}/cart`);
  }

  addAuctionToCart(userId: string, auctionId: string): void {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':  'application/json'
      })
    };
    const url = this.baseUrl + `/api/user/${userId}/cart/${auctionId}`;
    const body = JSON.stringify({});
    this.http.post(url, body, httpOptions).subscribe();
  }

}
