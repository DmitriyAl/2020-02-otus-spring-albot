import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {ProductCardService} from "./product-card.service";
import {NotifierService} from "angular-notifier";
import {HttpErrorResponse} from "@angular/common/http";
import {Product} from "../../model/product";

@Component({
  selector: 'app-product-card',
  templateUrl: './product-card.component.html',
  styleUrls: ['./product-card.component.less']
})
export class ProductCardComponent implements OnInit {
  @Input() product: Product;
  @Input() removable: boolean = false;
  @Input() selectable: boolean = false;
  @Output() cardRemoved: EventEmitter<Product> = new EventEmitter<Product>();

  constructor(private service: ProductCardService, private notifier: NotifierService) {
  }

  ngOnInit(): void {
  }

  removeProduct() {
    this.service.removeProduct(this.product.productDto.id).subscribe(() => {
      this.cardRemoved.emit(this.product);
    }, error => {
      const responseError = error as HttpErrorResponse;
      var ids = responseError.error.message;
      this.notifier.notify('error', 'Заказы ' + ids + ' содержат данный продукт')
    })
  }
}
