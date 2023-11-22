import { Component, inject } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styles: [],
})
export class MenuComponent {
  private router = inject(Router);

  public sidebarItems = [
    { label: 'Inicio', icon: 'home', url: '/admin' },
    { label: 'POS', icon: 'point_of_sales', url: './pos' },
    { label: 'Caja', icon: 'euro', url: './cashbox' },
    {
      label: 'Categorias',
      icon: 'category',
      url: '/admin/inventory/categories',
    },
    { label: 'Gastos', icon: 'payments', url: './expenses' },
    { label: 'Mesas', icon: 'table_bar', url: './tables' },
    {
      label: 'Productos',
      icon: 'inventory_2',
      url: '/admin/inventory/products',
    },
    { label: 'Usuarios', icon: 'groups', url: './users' },
  ];

  logout() {
    localStorage.clear();
    this.router.navigate(['/auth/login']);
  }
}
