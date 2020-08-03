import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {map} from "rxjs/operators";
import {JwtResponse} from "./model/jwt-response";

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  constructor(private http: HttpClient) {
  }

  authenticate(username, password) {
    return this.http.post<JwtResponse>('http://localhost:8080/authenticate', {username, password}).pipe(map(userData => {
      sessionStorage.setItem('username', username);
      let tokenStr = 'Bearer ' + userData.jwtToken;
      sessionStorage.setItem('token', tokenStr);
      return userData;
    }))
  }

  isUserLoggedIn() {
    let user = sessionStorage.getItem('username')
    //console.log(!(user === null))
    return !(user === null)
  }

  logOut() {
    sessionStorage.removeItem('username')
  }
}
