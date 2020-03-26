import { Injectable } from '@angular/core';
import {ProductDto} from "../../dto/productDto";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {api} from "../../utils/api";
import {NoteDto} from "../../dto/noteDto";

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(private http: HttpClient) {
  }

  public getProduct(id: number): Observable<ProductDto> {
    return this.http.get<ProductDto>(api.products + `/${id}`)
  }

  public getNotesToProduct(productId: number): Observable<NoteDto[]> {
    return this.http.get<NoteDto[]>(api.notes + `/${productId}`)
  }
}
