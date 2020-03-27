import {ProductDto} from "../dto/productDto";

export class Product {
  productDto: ProductDto;
  selected: boolean = false;

  constructor(dto?: ProductDto) {
    if (dto) {
      this.productDto = dto;
    } else {
      this.productDto = new ProductDto();
    }
  }
}
