import { Component, OnInit } from '@angular/core';
import {GlobalConstants} from '../common/global-constants';
import {AuctionService} from '../auction.service';
import {Auction} from '../auction';
import {Email} from '../email';

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html'
})
export class AccountComponent implements OnInit {
  loginInfo: string;
  auctions: Auction[];

  constructor(private auctionService: AuctionService) { }

  ngOnInit(): void {
    this.loginInfo = GlobalConstants.username;
    this.getAuctions();
  }

  logout(): void {
    GlobalConstants.username = null;
  }

  getAuctions(): void{
    this.auctionService.getAuctions().subscribe(auctions => this.auctions = auctions.filter(auction => auction.seller === this.loginInfo));
  }
}
