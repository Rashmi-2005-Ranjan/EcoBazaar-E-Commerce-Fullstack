import { Routes } from '@angular/router';

export const routes: Routes = [
  {
    path: '',
    loadComponent: () =>
      import('./Modules/feature/Components/home/home.component').then(
        (m) => m.HomeComponent
      ),
  },
  {
    path: 'cart',
    loadComponent: () =>
      import('./Modules/feature/Components/cart/cart.component').then(
        (m) => m.CartComponent
      ),
  },
  {
    path: 'product-details/:id',
    loadComponent: () =>
      import(
        './Modules/feature/Components/product-details/product-details.component'
      ).then((m) => m.ProductDetailsComponent),
  },
  {
    path: 'checkout',
    loadComponent: () =>
      import('./Modules/feature/Components/checkout/checkout.component').then(
        (m) => m.CheckoutComponent
      ),
  },
  {
    path: 'checkout/payment/:id',
    loadComponent: () =>
      import('./Modules/feature/Components/payment/payment.component').then(
        (m) => m.PaymentComponent
      ),
  },
  {
    path: 'payment-success',
    loadComponent: () =>
      import(
        './Modules/feature/Components/payment-success/payment-success.component'
      ).then((m) => m.PaymentSuccessComponent),
  },
  {
    path: 'account/orders',
    loadComponent: () =>
      import('./Modules/feature/Components/order/order.component').then(
        (m) => m.OrderComponent
      ),
  },
  {
    path:'order/:id',loadComponent:()=>import('./Modules/feature/Components/order-details/order-details.component').then(m=>m.OrderDetailsComponent)
  },
  //* Routing with parameters In Angular Lazy Loading
  {
    path: ':lavelOne/:lavelTwo/:lavelThree',
    loadComponent: () =>
      import('./Modules/feature/Components/products/products.component').then(
        (m) => m.ProductsComponent
      ),
  },
];
