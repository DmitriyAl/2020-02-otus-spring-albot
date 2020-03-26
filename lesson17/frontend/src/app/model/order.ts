import {OrderDto} from "../dto/orderDto";
import {Product} from "./product";

export class Order {
  orderDto: OrderDto;
  products: Product[];

  constructor(dto: OrderDto) {
    this.orderDto = dto;
    this.products = dto.products.map(p => new Product(p))
  }
}
