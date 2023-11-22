import {
  AfterViewInit,
  Component,
  OnInit,
  ViewChild,
  inject,
} from '@angular/core';
import { HotToastService } from '@ngneat/hot-toast';
import { Category } from 'src/app/models/category';
import { CategoriesService } from 'src/app/services/categories.service';
import * as bootstrap from 'bootstrap';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSort } from '@angular/material/sort';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
declare var window: any;

@Component({
  selector: 'app-categories',
  templateUrl: './categories.component.html',
  styles: [],
})
export class CategoriesComponent implements OnInit, AfterViewInit {
  @ViewChild('modalCategory') modal!: any;
  @ViewChild(MatSort) sort!: MatSort;
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  public dataSource: any;

  public categoryModal: any;
  public modalTitle: string = '';
  public categoryService = inject(CategoriesService);
  public toast = inject(HotToastService);
  public fb = inject(FormBuilder);
  public displayedColumns: string[] = ['name', 'active', 'actions'];

  ngAfterViewInit() {
    this.categoryModal = new bootstrap.Modal(this.modal.nativeElement!);
  }

  ngOnInit(): void {
    this.loadCategories();
  }

  /** Category Form */
  formCategory: FormGroup = this.fb.group({
    name: ['', [Validators.required, Validators.minLength(3)]],
  });

  /** Load all categories */
  loadCategories(): void {
    this.categoryService.loadAllCategoriries().subscribe((res: any) => {
      this.dataSource = new MatTableDataSource(res.content);
      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
    });
  }

  /** Open modal for a new category */
  newCategory() {
    this.categoryModal.show();
    this.modalTitle = 'Nueva Categoria';
  }

  editCategory(category: Category) {
    this.modalTitle = 'Edición de Categoria';
    this.formCategory.reset(category);
    this.categoryModal.show();
  }

  closeModal() {
    this.categoryModal.hide();
    this.formCategory.reset();
  }

  disableEnableCategory(category: Category) {
    this.categoryService.disableEnableCategory(category).subscribe((res) => {
      this.toast.success(`${category.name}, actualizada correctamente`);
      this.loadCategories();
    });
  }

  submit() {
    if (this.formCategory.invalid) {
      this.toast.info('Todos los campos son obligatorios');
      return;
    }

    this.categoryService
      .createCategory(this.formCategory.value)
      .subscribe((res: Category) => {
        this.toast.success(`${res.name}, se registro correctamente`);
        this.loadCategories();
        this.categoryModal.hide();
        this.formCategory.reset();
      });
  }

  isValid(field: any) {
    return (
      this.formCategory.controls[field]?.errors &&
      this.formCategory.controls[field]?.touched
    );
  }
}
