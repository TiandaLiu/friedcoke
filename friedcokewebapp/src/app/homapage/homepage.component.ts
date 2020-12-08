import { Component, OnInit } from '@angular/core';
import {AuctionService} from '../auction.service';
import {Auction} from '../auction';
import { GlobalConstants } from '../common/global-constants';

@Component({
    selector: 'app-homepage',
    templateUrl: './homepage.component.html'
  })

export class HomepageComponent implements OnInit {
    auctions: Auction[];
    loginInfo: string;
    keyword: string;

    constructor(private auctionService: AuctionService) { }

    ngOnInit(): void {
      this.loginInfo = GlobalConstants.username;
      this.getAuctions();
    }

    getAuctions(): void {
      this.auctionService.getAuctions().subscribe(auctions => this.auctions = auctions.filter(auction => auction.status === 'active'));
      //   .subscribe(response => {
      //   this.auctions = (JSON.parse(JSON.stringify(response)) as Auction[]).filter(auction => auction.status === 'active');
      // });
    }

    logout(): void {
      // console.log('logout');
      GlobalConstants.username = null;
    }

    getAuctionsByKeyword(): void {
      console.log(this.keyword);
      this.auctionService.getAuctions()
        .subscribe(auctions => this.auctions = auctions.filter(auction => auction.item_name.includes(this.keyword) || auction.category.includes(this.keyword)));
    }
}
