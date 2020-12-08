import { Component, OnInit } from '@angular/core';
import {Auction} from '../auction';
import {AuctionService} from '../auction.service';
import {Router} from '@angular/router';
import {GlobalConstants} from '../common/global-constants';

@Component({
  selector: 'app-new-auction',
  templateUrl: './new-auction.component.html'
})
export class NewAuctionComponent implements OnInit {
  auction: any;

  constructor(private auctionService: AuctionService,
              private router: Router) { }

  ngOnInit(): void {
    this.auction =
      {
        id: '',
        item_name: '',
        description: '',
        category: '',
        startTime: '0000-00-00 00:00:00',
        endTime: '0000-00-00 00:00:00',
        status: 'active',
        highest_bidder: '',
        seller: GlobalConstants.username,
        start_price: 0,
        curr_price: 0,
        buynow_price: 0,
        flag: false
      };
  }

  postNewAuction(): void {
    this.auction.curr_price = this.auction.start_price;
    this.auctionService.addAuction(this.auction as Auction);
    this.router.navigate(['/success']);
  }
}
