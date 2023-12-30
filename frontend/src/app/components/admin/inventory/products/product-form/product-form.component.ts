import {
  Component,
  EventEmitter,
  Input,
  OnInit,
  Output,
  ViewChild,
  inject,
} from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Category } from 'src/app/models/category';
import { Product } from 'src/app/models/product';
import { CategoriesService } from 'src/app/services/categories.service';

@Component({
  selector: 'app-product-form',
  templateUrl: './product-form.component.html',
  styles: ``,
})
export class ProductFormComponent implements OnInit {
  @Input() product!: Product;
  @ViewChild('productModal') modal!: any;
  @Input()
  productModal: any;
  @Output()
  closeModal = new EventEmitter<boolean>();
  private fb = inject(FormBuilder);
  private categoryServices = inject(CategoriesService);
  public categories: Category[] = [];

  productForm: FormGroup = this.fb.group({
    id: [null],
    name: ['', [Validators.required, Validators.minLength(3)]],
    categoryId: ['', [Validators.required]],
    price: [, [Validators.required]],
    hasStock: [false, [Validators.required]],
  });

  isValid(field: string) {}

  ngOnInit() {
    this.categoryServices.loadAllCategoriries().subscribe((res: any) => {
      this.categories = res.content;
    });
  }

  getFieldError(field: string): string | null {
    if (!this.productForm.controls[field]) return null;

    const errors = this.productForm.controls[field].errors || {};

    for (const key of Object.keys(errors)) {
      switch (key) {
        case 'required':
          return 'Este campo es requerido';
        case 'minlength':
          return `Mínimo ${errors['minlength'].requiredLength} caracteres`;
      }
    }
    return null;
  }

  submit() {
    
  }

  close() {
    this.closeModal.emit(true);
  }
}
