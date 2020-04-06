import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {ProductDto} from "../../dto/productDto";
import {api} from "../../utils/api";

@Injectable({
  providedIn: 'root'
})
export class ProductsService {

  constructor(private http: HttpClient) { }

  public getProducts(): Observable<ProductDto[]> {
    return this.http.get<ProductDto[]>(api.products)
  }
}
