import {Component, OnInit} from '@angular/core';
import {Order} from "../../model/order";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.less']
})
export class HomeComponent implements OnInit {
  order: Order;

  constructor() { }

  ngOnInit() {
  }

  updateOrder(order: Order) {
    this.order = order;
  }
}
