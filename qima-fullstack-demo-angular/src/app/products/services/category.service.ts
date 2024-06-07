import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Category } from '../model/gategory';
import { first, tap } from 'rxjs';

const headerDict = {
  'Content-Type': 'application/json',
}

const requestOptions = {
  headers: new HttpHeaders(headerDict)
};

const baseUrl = 'http://localhost:8080/api/category';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  constructor(private httpClient: HttpClient) { }

  findAll() {
    return this.httpClient.get<Category[]>(baseUrl, requestOptions).pipe(
      first(),
    );
  }

}
