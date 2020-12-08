import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms'; // <-- NgModel lives here

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeroesComponent } from './heroes/heroes.component';
import { HeroDetailComponent } from './hero-detail/hero-detail.component';
import { MessagesComponent } from './messages/messages.component';

import { HomepageComponent } from './homapage/homepage.component';
import { AuctionDetailComponent } from './auction-detail/auction-detail.component';
import {LoginComponent} from './login/login.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {SuccessComponent} from './success/success.component';
import {AdminComponent} from './admin/admin.component';
import {AccountComponent} from './account/account.component';
import {NewAuctionComponent} from './new-auction/new-auction.component';
import {WatchlistComponent} from './watchlist/watchlist.component';
import {ShoppingCartComponent} from './shopping-cart/shopping.component';
import {NewEmailComponent} from './new-email/new-email.component';
import {EmailsComponent} from './emails/emails.component';

@NgModule({
  declarations: [
    AppComponent,
    HeroesComponent,
    HeroDetailComponent,
    MessagesComponent,

    HomepageComponent,
    AuctionDetailComponent,
    LoginComponent,
    SuccessComponent,
    AdminComponent,
    AccountComponent,
    NewAuctionComponent,
    WatchlistComponent,
    ShoppingCartComponent,
    NewEmailComponent,
    EmailsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    BrowserAnimationsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
