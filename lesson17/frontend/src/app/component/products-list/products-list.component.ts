import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {ProductsListService} from "./products-list.service";
import {Product} from "../../model/product";

@Component({
  selector: 'app-products-list',
  templateUrl: './products-list.component.html',
  styleUrls: ['./products-list.component.less']
})
export class ProductsListComponent implements OnInit {
  @Input() products: Product[];
  @Input() removable: boolean = false;
  @Input() selectable: boolean = false;
  @Output() updateList: EventEmitter<void> = new EventEmitter<void>();

  constructor(private service: ProductsListService) {
  }

  ngOnInit() {
  }

  updateCards(product: Product) {
    this.updateList.emit();
  }
}
