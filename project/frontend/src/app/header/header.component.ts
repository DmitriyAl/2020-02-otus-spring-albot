import { Component, OnInit } from '@angular/core';
import {AuthenticationService} from "../security/authentication.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.less']
})
export class HeaderComponent implements OnInit {

  constructor(private authentication: AuthenticationService, private router: Router) { }

  ngOnInit() {
  }

  logout() {
    this.authentication.logOut();
    this.router.navigate(['login']);
  }

  isLoggedIn(): boolean {
    return this.authentication.isUserLoggedIn()
  }
}
