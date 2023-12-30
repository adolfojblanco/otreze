import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { InventoryRoutingModule } from './inventory-routing.module';
import { InventoryComponent } from './inventory.component';
import { RouterModule } from '@angular/router';
import { MaterialModule } from '../../shared/material/material.module';
import { CategoriesComponent } from './categories/categories.component';
import { ProductsComponent } from './products/products.component';
import { ReactiveFormsModule } from '@angular/forms';
import { ProductFormComponent } from './products/product-form/product-form.component';

@NgModule({
  declarations: [
    InventoryComponent,
    CategoriesComponent,
    ProductsComponent,
    ProductFormComponent,
  ],
  imports: [
    CommonModule,
    InventoryRoutingModule,
    RouterModule,
    MaterialModule,
    ReactiveFormsModule,
  ],
})
export class InventoryModule {}
