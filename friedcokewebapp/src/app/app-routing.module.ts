import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { DashboardComponent } from './dashboard/dashboard.component';
import { HeroesComponent } from './heroes/heroes.component';
import { HeroDetailComponent } from './hero-detail/hero-detail.component';
import {HomepageComponent} from './homapage/homepage.component';
import {AuctionDetailComponent} from './auction-detail/auction-detail.component';
import {LoginComponent} from './login/login.component';
import {SuccessComponent} from './success/success.component';
import {AdminComponent} from './admin/admin.component';
import {AccountComponent} from './account/account.component';
import {NewAuctionComponent} from './new-auction/new-auction.component';
import {ShoppingCartComponent} from './shopping-cart/shopping.component';
import {WatchlistComponent} from './watchlist/watchlist.component';
import {NewEmailComponent} from './new-email/new-email.component';
import {EmailsComponent} from './emails/emails.component';



const routes: Routes = [
  // { path: '', redirectTo: '/dashboard', pathMatch: 'full' },
  { path: 'dashboard', component: DashboardComponent },
  { path: 'detail/:id', component: HeroDetailComponent },
  { path: 'heroes', component: HeroesComponent },

  { path: '', redirectTo: '/homepage', pathMatch: 'full' },
  { path: 'homepage', component: HomepageComponent },
  { path: 'auction/:id', component: AuctionDetailComponent },
  { path: 'login', component: LoginComponent },
  { path: 'success', component: SuccessComponent },
  { path: 'admin', component: AdminComponent },
  { path: 'account', component: AccountComponent },
  { path: 'new-auction', component: NewAuctionComponent },
  { path: 'new-email', component: NewEmailComponent },
  { path: 'shopping-cart', component: ShoppingCartComponent },
  { path: 'watchlist', component: WatchlistComponent },
  { path: 'emails', component: EmailsComponent },
];

@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule {}
