import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { Observable, of } from 'rxjs';
import { ProductService } from '../services/product.service';
import { Product } from '../model/product';

@Injectable({
  providedIn: 'root'
})
export class ProductResolver  {

  constructor(private service: ProductService) { }

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Product> {
    if (route.params && route.params['id']) {
      var response = this.service.findById(route.params['id']);
      return response;
    }
    return of({
      id: 0,
      name: '',
      description: '',
      price: 0,
      categoryId: 0,
      categoryName: '',
      available: false
    } as Product);
  }
}
