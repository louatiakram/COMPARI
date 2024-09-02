import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Product } from '../modules/products/products.module';

@Injectable({
  providedIn: 'root',
})
export class ProductService {
  private apiUrl = 'http://localhost:8082/api/products'; // Adjust the URL if needed
  private imageUrlBase = 'http://localhost:8082/api/images/list/'; // URL for image listing API

  constructor(private http: HttpClient) {}

  getAllProducts(): Observable<Product[]> {
    return this.http.get<Product[]>(this.apiUrl);
  }

  getProductById(id: number): Observable<Product> {
    return this.http.get<Product>(`${this.apiUrl}/${id}`);
  }

  // Get image list for a product
  getProductImages(productName: string): Observable<string[]> {
    return this.http.get<string[]>(`${this.imageUrlBase}${encodeURIComponent(productName)}`);
  }
}
