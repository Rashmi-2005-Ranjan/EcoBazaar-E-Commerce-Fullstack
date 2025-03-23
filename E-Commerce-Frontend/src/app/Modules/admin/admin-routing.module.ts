import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {
    path: '',
    loadComponent: () =>
      import('./Components/admin.component').then((m) => m.AdminComponent),
    children: [
      {
        path: '',
        loadComponent: () =>
          import('./Components/dashboard/dashboard.component').then(
            (m) => m.DashboardComponent
          ),
      },
      {
        path: 'products',
        loadComponent: () =>
          import('./Components/admin-products/admin-products.component').then(
            (m) => m.AdminProductsComponent
          ),
      },
      {
        path: 'orders',
        loadComponent: () =>
          import('./Components/orders-table/orders-table.component').then(
            (m) => m.OrdersTableComponent
          ),
      },
      {
        path: 'customers',
        loadComponent: () =>
          import('./Components/customers/customers.component').then(
            (m) => m.CustomersComponent
          ),
      },
      {
        path: 'product/create',
        loadComponent: () =>
          import('./Components/create-product/create-product.component').then(
            (m) => m.CreateProductComponent
          ),
      },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class AdminRoutingModule {}
