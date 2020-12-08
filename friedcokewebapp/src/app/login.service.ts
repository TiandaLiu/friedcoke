import { Injectable } from '@angular/core';
import { GlobalConstants } from './common/global-constants';

@Injectable({ providedIn: 'root' })
export class LoginService {

  constructor() { }

  verifyLogin(username: string, password: string): number {
    if (password === 'cuowu') {
      return 0;
    }
    if (true) {
      GlobalConstants.username = username;
      return 1;
    } else {
      return 0;
    }
  }
}
