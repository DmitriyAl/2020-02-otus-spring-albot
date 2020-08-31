import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {OrdersService} from "./orders.service";
import {Order} from "../../model/order";
import {AuthenticationService} from "../../security/authentication.service";

@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styleUrls: ['./orders.component.less']
})
export class OrdersComponent implements OnInit {
  orders: Order[];
  selectedOrder: Order;
  @Output() orderSelected: EventEmitter<Order> = new EventEmitter<Order>();

  constructor(private service: OrdersService, private authentication: AuthenticationService) {
  }

  ngOnInit() {
    this.updateOrders();
  }

  private updateOrders() {
    this.service.getOrders().subscribe(orders => this.orders = orders.map(o => new Order(o)))
  }

  updateOrder(order: Order): void {
    this.service.getOrder(order.orderDto.id).subscribe(orderDto => {
      this.selectedOrder = new Order(orderDto);
      this.orderSelected.emit(this.selectedOrder);
    }, error => {
      console.log(error);
      this.updateOrders();
    });
  }

  isSelected(order: Order): boolean {
    return this.selectedOrder != null && this.selectedOrder.orderDto.id == order.orderDto.id;
  }

  removeOrder(event: MouseEvent, order: Order): void {
    event.stopPropagation();
    this.service.removeOrder(order.orderDto.id).subscribe(() => {
      this.updateOrders();
      this.orderSelected.emit(null);
    }, error => console.log(error));
  }

  isAdmin() {
    return this.authentication.isAdmin()
  }
}
