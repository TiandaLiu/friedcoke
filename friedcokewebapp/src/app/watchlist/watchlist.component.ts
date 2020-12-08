import { Component, OnInit } from '@angular/core';
import {GlobalConstants} from '../common/global-constants';
import {Auction} from '../auction';
import {UserService} from '../user.service';

@Component({
  selector: 'app-watchlist',
  templateUrl: './watchlist.component.html'
})
export class WatchlistComponent implements OnInit {
  loginInfo: string;
  watchlist: Auction[];

  constructor(private userService: UserService) { }

  ngOnInit(): void {
    this.loginInfo = GlobalConstants.username;
    this.getWatchlist();
  }

  getWatchlist(): void {
    this.userService.getWatchlist(this.loginInfo).subscribe(auctions => this.watchlist = auctions);
  }
}
