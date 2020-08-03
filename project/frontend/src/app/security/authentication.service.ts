import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {map} from "rxjs/operators";
import {JwtResponse} from "./model/jwt-response";
import {api} from "../utils/api";
import {JwtHelperService} from "@auth0/angular-jwt";
import {StoredItems} from "../model/stored-items";
import {Authority} from "../model/authority";

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  constructor(private http: HttpClient) {
  }

  authenticate(username, password) {
    return this.http.post<JwtResponse>(api.authenticate, {username, password}).pipe(map(userData => {
      sessionStorage.setItem(StoredItems.USERNAME, username);
      sessionStorage.setItem(StoredItems.TOKEN, userData.jwtToken);
      const helper = new JwtHelperService();
      const decodedToken = helper.decodeToken(userData.jwtToken);
      sessionStorage.setItem(StoredItems.AUTHORITIES, decodedToken.authorities.map(a => a.authority))
      return userData;
    }))
  }

  isUserLoggedIn() {
    let user = sessionStorage.getItem(StoredItems.USERNAME)
    //console.log(!(user === null))
    return !(user === null)
  }

  isAdmin() {
    return sessionStorage.getItem(StoredItems.AUTHORITIES).includes(Authority.ROLE_ADMIN);
  }

  logOut() {
    sessionStorage.removeItem(StoredItems.USERNAME)
    sessionStorage.removeItem(StoredItems.TOKEN)
    sessionStorage.removeItem(StoredItems.AUTHORITIES)
  }
}
