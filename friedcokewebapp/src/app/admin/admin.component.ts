import { Component, OnInit } from '@angular/core';
import {Auction} from '../auction';
import {User} from '../user';
import {Email} from '../email';
import {AuctionService} from '../auction.service';
import {EmailService} from '../email.service';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html'
})
export class AdminComponent implements OnInit {
  auctions: Auction[];
  users: User[];
  emails: Email[];
  selectedAuction: Auction;

  constructor(private auctionService: AuctionService,
              private emailService: EmailService) { }

  ngOnInit(): void {
    this.getAuctions();
    this.getAllEmails();
  }

  getUsers(): void {

  }

  getAllEmails(): void {
    this.emailService.getAllEmail().subscribe(emails => this.emails = emails.filter(email => email.receiver === 'admin'));
  }

  getAuctions(): void{
    this.auctionService.getAuctions().subscribe(auctions => this.auctions = auctions.filter(auction => auction.status === 'active'));
  }

  terminateAuction(auctionId: string): void {
    this.auctionService.getAuctionById(auctionId)
      .subscribe(auction => this.selectedAuction = auction);
    this.selectedAuction.status = 'terminated';
    this.auctionService.updateAuction(auctionId, this.selectedAuction);
    window.alert(this.selectedAuction.item_name + 'terminated!');
  }

}
