import { Component, OnInit } from '@angular/core';
import {Auction} from '../auction';
import {GlobalConstants} from '../common/global-constants';

@Component({
  selector: 'app-shopping-cart',
  templateUrl: './shopping-cart.component.html'
})
export class ShoppingCartComponent implements OnInit {
  loginInfo: string;
  cart: Auction[];

  constructor() { }

  ngOnInit(): void {
    this.loginInfo = GlobalConstants.username;
  }

  getCart() {

  }
}
