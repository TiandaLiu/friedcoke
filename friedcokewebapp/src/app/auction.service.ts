import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { HttpHeaders } from '@angular/common/http';

import {Auction} from './auction';
import {HttpClient} from '@angular/common/http';
import {GlobalConstants} from './common/global-constants';

@Injectable({ providedIn: 'root' })
export class AuctionService {
  baseUrl = GlobalConstants.apiURL;

  constructor(private http: HttpClient) { }

  getAuctions(): Observable<any> {
    return this.http.get(this.baseUrl + '/api/auction');
  }

  getAuctionById(id: string): Observable<any> {
    return this.http.get(this.baseUrl + `/api/auction/${id}`);
  }

  updateAuction(id: string, newAuction: Auction): void {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':  'application/json'
      })
    };
    const body = JSON.stringify(newAuction);
    const url = this.baseUrl + `/api/auction/${id}`;
    this.http.put<Auction>(url, body, httpOptions).subscribe();
  }

  addAuction(auction: Auction): void {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':  'application/json'
      })
    };
    const url = this.baseUrl + '/api/auction';
    const body = JSON.stringify(auction);
    this.http.post(url, body, httpOptions).subscribe();
  }

}
