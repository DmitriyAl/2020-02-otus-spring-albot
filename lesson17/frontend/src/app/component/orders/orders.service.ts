import { Injectable } from '@angular/core';
import {OrderDto} from "../../dto/orderDto";
import {HttpClient, HttpParams} from "@angular/common/http";
import {api} from "../../utils/api";
import {Observable} from "rxjs";
import {ProductDto} from "../../dto/productDto";

@Injectable({
  providedIn: 'root'
})
export class OrdersService {

  constructor(private http: HttpClient) { }

  public getOrders(): Observable<OrderDto[]> {
    return this.http.get<OrderDto[]>(api.orders)
  }

  public getProductsForOrder(id: number): Observable<ProductDto[]> {
    return this.http.get<ProductDto[]>(api.orders + `/${id}`)
  }

  public removeOrder(id: number): Observable<void> {
    const params = new HttpParams().append('id', id.toString());
    return this.http.delete<void>(api.orders, {params: params})
  }

  public createNewOrder(productIds: number[]): Observable<OrderDto> {
    return this.http.post<OrderDto>(api.orders, productIds)
  }
}
