import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {ProductCardService} from "./product-card.service";
import {NotifierService} from "angular-notifier";
import {HttpErrorResponse} from "@angular/common/http";
import {Product} from "../../model/product";
import {TranslateService} from "@ngx-translate/core";

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

  constructor(private service: ProductCardService, private notifier: NotifierService, private translateService: TranslateService) {
  }

  ngOnInit(): void {
  }

  removeProduct() {
    this.service.removeProduct(this.product.productDto.id).subscribe(() => {
      this.cardRemoved.emit(this.product);
    }, error => {
      const responseError = error as HttpErrorResponse;
      var ids = responseError.error.message;
      if (ids.includes(',')) {
        this.translateService.get('products.deletionError.multiple', {'orders': ids})
          .subscribe(translations => this.notifier.notify('error', translations));
      } else {
        this.translateService.get('products.deletionError.single', {'order': ids})
          .subscribe(translations => this.notifier.notify('error', translations));
      }
    })
  }
}
