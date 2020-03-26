import {Component, OnInit} from '@angular/core';
import {Product} from "../../model/product";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.less']
})
export class HomeComponent implements OnInit {
  products: Product[];

  constructor() { }

  ngOnInit() {
  }

  updateProducts(products: Product[]) {
    this.products = products;
  }
}
