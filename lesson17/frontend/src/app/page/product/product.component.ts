import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {ProductDto} from "../../dto/productDto";
import {ProductService} from "./product.service";
import {NoteDto} from "../../dto/noteDto";

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.less']
})
export class ProductComponent implements OnInit {
  product: ProductDto;
  notes: NoteDto[];

  constructor(private route: ActivatedRoute, private service: ProductService) {
  }

  ngOnInit(): void {
    const id = +this.route.snapshot.paramMap.get('id');
    this.service.getProduct(id).subscribe(product => this.product = product, error => console.log(error));
    this.service.getNotesToProduct(id).subscribe(notes => this.notes = notes, error => console.log(error));
  }
}
