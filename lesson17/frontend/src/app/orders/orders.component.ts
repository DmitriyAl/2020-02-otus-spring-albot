import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Order} from "../model/order";

@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styleUrls: ['./orders.component.less']
})
export class OrdersComponent implements OnInit {
  orders: Order[];

  constructor(private http: HttpClient) { }

  ngOnInit() {
    this.http.get<Order[]>('http://localhost:8080/orders').subscribe(orders => this.orders = orders)
  }
}
