import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";
import {api} from "../../utils/api";

@Injectable({
  providedIn: 'root'
})
export class ProductCardService {

  constructor(private http: HttpClient) { }

  public removeProduct(id: number): Observable<void> {
    const params = new HttpParams().append('id', id.toString());
    return this.http.delete<void>(api.products, {params})
  }
}
