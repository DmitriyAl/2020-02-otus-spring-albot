import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {OrderDto} from "../../dto/orderDto";
import {OrdersService} from "./orders.service";
import {Product} from "../../model/product";

@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styleUrls: ['./orders.component.less']
})
export class OrdersComponent implements OnInit {
  orders: OrderDto[];
  selectedOrder: OrderDto;
  @Output() productsUpdated: EventEmitter<Product[]> = new EventEmitter<Product[]>();

  constructor(private service: OrdersService) {
  }

  ngOnInit() {
    this.updateOrders();
  }

  private updateOrders() {
    this.service.getOrders().subscribe(orders => this.orders = orders)
  }

  updateProducts(order: OrderDto): void {
    this.service.getProductsForOrder(order.id).subscribe(products => {
      this.selectedOrder = order;
      this.productsUpdated.emit(products.map(p => new Product(p)));
    }, error => {
      console.log(error)
    });
  }

  isSelected(order: OrderDto): boolean {
    return this.selectedOrder == order;
  }

  removeOrder(event: MouseEvent, order: OrderDto): void {
    event.stopPropagation();
    this.service.removeOrder(order.id).subscribe(() => {
      this.updateOrders();
      this.selectedOrder = null;
    }, error => console.log(error));
  }
}
