import {Component, OnInit} from '@angular/core';
import {ProductsService} from "./products.service";
import {Product} from "../../model/product";
import {OrdersService} from "../../component/orders/orders.service";
import {NotifierService} from "angular-notifier";
import {Router} from "@angular/router";

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.less']
})
export class ProductsComponent implements OnInit {
  products: Product[];
  editMode: boolean = false;

  constructor(private productsService: ProductsService,
              private ordersService: OrdersService,
              private notifier: NotifierService,
              private router: Router) {
  }

  ngOnInit(): void {
    this.updateProducts();
  }

  updateProducts() {
    this.productsService.getProducts()
      .subscribe(dtos => this.products = dtos.map(p => new Product(p)), error => console.log(error));
  }

  newOrderMode(isEditMode: boolean) {
    this.editMode = isEditMode;
  }

  createOrder() {
    const addToOrder: number[] = [];
    this.products.forEach(p => {
      if (p.selected) {
        addToOrder.push(p.productDto.id)
      }
    });
    this.ordersService.createNewOrder(addToOrder).subscribe(order => {
      this.notifier.notify('success', order.id.toString());
      this.router.navigate([''])
    });
  }
}
