import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Category } from '../models/category';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class CategoriesService {
  private readonly urlEndPoint: string = `${environment.apiUrl}/categories`;
  private http = inject(HttpClient);

  loadAllCategoriries(): Observable<Category[]> {
    return this.http.get<Category[]>(this.urlEndPoint);
  }

  loadActiveCategories() {
     return this.http.get<Category[]>(`${this.urlEndPoint}/active`);
  }

  createCategory(category: Category): Observable<Category> {
    return this.http.post<Category>(this.urlEndPoint, category);
  }

  disableEnableCategory(category: Category): Observable<Category> {
    return this.http.put<Category>(
      `${this.urlEndPoint}/${category.id}/disabled`,
      category.id
    );
  }

  editCategory(category: Category): Observable<Category> {
    return this.http.put<Category>(
      `${this.urlEndPoint}/${category.id}`,
      category
    );
  }
}
