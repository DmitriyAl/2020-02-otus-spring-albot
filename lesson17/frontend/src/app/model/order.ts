import {OrderDto} from "../dto/orderDto";

export class Order {
  orderDto: OrderDto;

  constructor(dto: OrderDto) {
    this.orderDto = dto;
  }
}
