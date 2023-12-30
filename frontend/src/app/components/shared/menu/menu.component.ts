import {
  AfterViewInit,
  Component,
  ElementRef,
  OnInit,
  ViewChild,
  inject,
} from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styles: [],
})
export class MenuComponent implements OnInit, AfterViewInit {
  @ViewChild('sidebarCollapse') collapse!: ElementRef;
  @ViewChild('mainWrapper') wrapper!: ElementRef;

  private router = inject(Router);
  private authService = inject(AuthService);
  public user!: User;

  public sidebarItems = [
    { label: 'Inicio', icon: 'home', url: '/admin' },
    { label: 'POS', icon: 'point_of_sales', url: './pos' },
    { label: 'Caja', icon: 'euro', url: './cashbox' },
    {
      label: 'Categorias',
      icon: 'ti ti-category',
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

  ngOnInit(): void {
    this.loadUser();
    //mini-sidebar show-sidebar
  }

  showMenu() {
    const element: HTMLElement = this.wrapper.nativeElement;

    if (element.classList.contains('show-sidebar')) {
      console.log('Lo tngo');
      element.classList.remove('mini-sidebar', 'show-sidebar');
    } else {
      console.log('No lo tengo');
      this.wrapper.nativeElement.classList.add('mini-sidebar', 'show-sidebar');
    }
  }

  ngAfterViewInit() {}

  /** Load logged user */
  loadUser() {
    this.user = this.authService.getUserDetails();
  }

  logout() {
    localStorage.clear();
    this.router.navigate(['/auth/login']);
  }
}
