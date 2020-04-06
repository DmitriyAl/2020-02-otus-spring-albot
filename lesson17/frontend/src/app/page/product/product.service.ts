import {Injectable} from '@angular/core';
import {ProductDto} from "../../dto/productDto";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {api} from "../../utils/api";

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(private http: HttpClient) {
  }

  public getProduct(id: number): Observable<ProductDto> {
    return this.http.get<ProductDto>(api.products + `/${id}`)
  }

  public updateProduct(product: ProductDto): Observable<ProductDto> {
    return this.http.put<ProductDto>(api.products, product)
  }
}
