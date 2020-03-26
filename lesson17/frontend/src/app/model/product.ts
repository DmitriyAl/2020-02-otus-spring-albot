import {ProductDto} from "../dto/productDto";

export class Product {
  productDto: ProductDto;
  selected: boolean = false;

  constructor(dto: ProductDto) {
    this.productDto = dto;
  }
}
