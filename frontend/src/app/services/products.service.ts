import { Injectable, inject } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Product } from '../models/product';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class ProductsService {
  private readonly urlEndPoint: string = `${environment.apiUrl}/products`;
  private http = inject(HttpClient);

  constructor() {}

  getAllProducts(): Observable<Product[]> {
    return this.http.get<Product[]>(this.urlEndPoint);
  }

  createProduct(product: Product) {
    console.log(product);
  }
}
