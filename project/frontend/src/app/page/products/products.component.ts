import {Component, OnInit} from '@angular/core';
import {ProductsService} from "./products.service";
import {Product} from "../../model/product";
import {OrdersService} from "../../component/orders/orders.service";
import {NotifierService} from "angular-notifier";
import {ActivatedRoute, Router} from "@angular/router";
import {OrderDto} from "../../dto/orderDto";
import {AuthenticationService} from "../../security/authentication.service";
import {TranslateService} from "@ngx-translate/core";

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.less']
})
export class ProductsComponent implements OnInit {
  orderId: number;
  products: Product[];
  editMode: boolean = false;

  constructor(private productsService: ProductsService,
              private ordersService: OrdersService,
              private notifier: NotifierService,
              private router: Router,
              private activatedRoute: ActivatedRoute,
              private authentication: AuthenticationService,
              private translateService: TranslateService) {
  }

  ngOnInit(): void {
    this.orderId = +this.activatedRoute.snapshot.queryParamMap.get('id');
    if (this.orderId) {
      this.editMode = true;
    }
    this.updateProducts();
  }

  updateProducts() {
    this.productsService.getProducts()
      .subscribe(dtos => {
        this.products = dtos.map(p => new Product(p));
        if (this.orderId) {
          this.ordersService.getOrder(this.orderId).subscribe(o => {
            this.products.forEach(p => o.products.forEach(op => {
              if (op.id == p.productDto.id) {
                p.selected = true;
              }
            }))
          }, error => console.log(error))
        }
      }, error => console.log(error));
  }

  newOrderMode(isEditMode: boolean) {
    this.editMode = isEditMode;
    if (this.orderId && !isEditMode) {
      this.router.navigate(['']);
    }
  }

  createOrder() {
    const order = new OrderDto();
    order.id = this.orderId;
    order.products = [];
    this.products.forEach(p => {
      if (p.selected) {
        order.products.push(p.productDto)
      }
    });
    this.ordersService.createNewOrder(order).subscribe(o => {
      if (order.id) {
        this.translateService.get('orders.changed', {number: o.id.toString()})
          .subscribe(translation => this.notifier.notify('success', translation))
      } else {
        this.translateService.get('orders.created', {number: o.id.toString()})
          .subscribe(translation => this.notifier.notify('success', translation))
      }
      this.router.navigate(['']);
    });
  }

  addNewProduct() {
    this.router.navigate(['/products/new'])
  }

  isAdmin() {
    return this.authentication.isAdmin()
  }
}
