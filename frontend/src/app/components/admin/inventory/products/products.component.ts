import {
  AfterViewInit,
  Component,
  inject,
  Input,
  OnInit,
  Output,
  ViewChild,
} from '@angular/core';
import { MatPaginator, PageEvent } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Product } from 'src/app/models/product';
import { ProductsService } from 'src/app/services/products.service';
import * as bootstrap from 'bootstrap';
import { error } from 'jquery';
declare var window: any;
@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styles: ``,
})
export class ProductsComponent implements OnInit, AfterViewInit {
  @ViewChild('productModal') modal!: any;
  @ViewChild(MatSort) sort!: MatSort;
  @ViewChild(MatPaginator) paginator!: MatPaginator;

  public dataSource!: MatTableDataSource<Product | MatPaginator>;
  @Output() productModal!: bootstrap.Modal;
  @Input() CloseModal: any;
  private productServices = inject(ProductsService);

  public modalTitle: String = '';
  public displayedColumns: string[] = [
    'name',
    'price',
    'category',
    'stock',
    'hasStock',
    'status',
    'actions',
  ];

  pageEvent?: PageEvent;

  ngAfterViewInit() {
    this.productModal = new bootstrap.Modal(this.modal.nativeElement!);
  }

  ngOnInit(): void {
    this.loadProduct();
  }

  loadProduct() {
    this.productServices.getAllProducts().subscribe((res: any) => {
      this.dataSource = res.content;
      this.dataSource.sortData = res.content;
    });
  }

  newProduct() {
    this.modalTitle = 'Nuevo Producto';
    this.productModal.show();
  }

  submitForm() {
    console.log('Submit');
  }

  editProduct(element: Product) {
    this.modalTitle = 'Editar Producto';
    this.productModal.show();
  }

  disableEnableProduct(element: Product) {}

  isValid() {}

  closeModal() {
    this.productModal.hide();
  }
}
