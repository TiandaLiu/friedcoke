import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';

import {AuctionService} from '../auction.service';
import {Auction} from '../auction';
import {GlobalConstants} from '../common/global-constants';
import {UserService} from '../user.service';

@Component({
  selector: 'app-auction-detail',
  templateUrl: './auction-detail.component.html'
})
export class AuctionDetailComponent implements OnInit {
  loginInfo: string;
  auction: Auction;
  biddingPrice: number;

  constructor(
    private route: ActivatedRoute,
    private auctionService: AuctionService,
    private userService: UserService,
    private location: Location
  ) {}

  ngOnInit(): void {
    this.loginInfo = GlobalConstants.username;
    this.getAuction();
  }

  getAuction(): void {
    const id = this.route.snapshot.paramMap.get('id');
    this.auctionService.getAuctionById(id)
      .subscribe(auction => this.auction = auction);
  }

  goBack(): void {
    this.location.back();
  }

  bid(): void {
    if (this.biddingPrice > this.auction.curr_price + 2) {
      this.auction.curr_price = this.biddingPrice;
      this.auction.highest_bidder = GlobalConstants.username;
      const id = this.route.snapshot.paramMap.get('id');
      this.auctionService.updateAuction(id, this.auction);
      window.alert(`Bidding successful!\nBidding price: ${this.biddingPrice}`);
    }
    else {
      window.alert('Bidding failed!');
    }
  }

  buyNow(): void {
    window.alert('buynow');
  }

  addToWatchList(): void {
    this.userService.addAuctionToWatchlist(this.loginInfo, this.auction.id);
    window.alert('Added item to watchlist.');
  }
}
