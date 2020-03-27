import {Component, OnInit} from '@angular/core';
import {Order} from "../../model/order";
import {Router} from "@angular/router";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.less']
})
export class HomeComponent implements OnInit {
  order: Order;

  constructor(private router: Router) { }

  ngOnInit() {
  }

  updateOrder(order: Order) {
    this.order = order;
  }

  changeOrder() {
    this.router.navigate(["/products"],{queryParams: {'id': this.order.orderDto.id}})
  }
}
