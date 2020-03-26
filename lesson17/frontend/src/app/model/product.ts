import {ProductDto} from "../dto/productDto";

export class Product {
  productDto: ProductDto;
  selected: boolean = true;

  constructor(dto: ProductDto) {
    this.productDto = dto;
  }
}
