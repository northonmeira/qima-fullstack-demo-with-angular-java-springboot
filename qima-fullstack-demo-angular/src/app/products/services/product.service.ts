import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, delay, first, of, tap } from 'rxjs';

import { Product } from '../model/product';

const headerDict = {
  'Content-Type': 'application/json',
}

const requestOptions = {
  headers: new HttpHeaders(headerDict)
};

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  private readonly baseUrl = 'http://localhost:8080/api/product';

  constructor(private httpClient: HttpClient) { }

  remove(id: number) {
    return this.httpClient.delete(`${this.baseUrl}/${id}`, requestOptions)
      .pipe(first());
  }

  save(record: Product) {
    return this.httpClient.post<Product>(this.baseUrl, record, requestOptions).pipe(
      first()
    );
  }

  update(record: Product) {
    return this.httpClient.put<Product>(this.baseUrl, record, requestOptions).pipe(
      first()
    );
  }

  findAll() {
    return this.httpClient.get<Product[]>(this.baseUrl, requestOptions).pipe(
      first()
    );
  }

  findById(id: string) {
    return this.httpClient.get<Product>(this.baseUrl + '/' + id, requestOptions).pipe(
      first()
    );
  }

}
