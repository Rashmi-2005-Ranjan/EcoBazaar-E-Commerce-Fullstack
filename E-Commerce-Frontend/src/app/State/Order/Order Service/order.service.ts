import { Injectable } from '@angular/core';
import { BASE_API_URL } from '../../../Config/api';
import { Store } from '@ngrx/store';
import { HttpClient } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { catchError, map, of } from 'rxjs';
import {
  createOrderFailure,
  createOrderSuccess,
  getOrderByIdFailure,
  getOrderByIdSuccess,
  getOrderHistoryFailure,
  getOrderHistorySuccess,
} from '../Order Action/order.action';

@Injectable({
  providedIn: 'root',
})
export class OrderService {
  private API_BASE_URL = BASE_API_URL;
  private headers;
  constructor(
    private store: Store,
    private http: HttpClient,
    private router: Router,
    private route: ActivatedRoute
  ) {
    this.headers = {
      Authorization: `Bearer ${localStorage.getItem('user')}`,
      'Content-Type': 'application/json',
    };
  }

  createOrder(reqData: any) {
    console.log('Create Order Service', reqData);
    const url = `${this.API_BASE_URL}/api/orders`;
    return this.http
      .post(url, reqData, { headers: this.headers })
      .pipe(
        map((data: any) => {
          console.log(`Order Created ${data}`);
          if (data.id) {
            this.router.navigate([`/checkout/payment/${data.id}`], {
              queryParams: { step: '3', order_id: data.id },
            });
          }
          console.log(`Order Created ${data}`);
          return createOrderSuccess({ payload: data });
        }),
        catchError((error: any) => {
          console.error('Error creating order:', error);
          return of(
            createOrderFailure({
              error:
                error.response && error.response.data
                  ? error.response.data
                  : error.message,
            })
          );
        })
      )
      .subscribe((action) => this.store.dispatch(action));
  }

  getOrderById(orderId: string) {
    const url = `${this.API_BASE_URL}/api/orders/${orderId}`;
    return this.http
      .get(url, { headers: this.headers })
      .pipe(
        map((data: any) => {
          console.log(`Order Data ${data}`);
          return getOrderByIdSuccess({ order: data });
        }),
        catchError((error: any) => {
          console.error('Error fetching order:', error);
          return of(
            getOrderByIdFailure({
              error:
                error.response && error.response.data
                  ? error.response.data
                  : error.message,
            })
          );
        })
      )
      .subscribe((action) => this.store.dispatch(action));
  }

  getOrderHistory() {
    const url = `${this.API_BASE_URL}/api/orders/user`;
    return this.http
      .get(url, { headers: this.headers })
      .pipe(
        map((data) => {
          console.log(`Order History Data ${data}`);
          return getOrderHistorySuccess({ orders: data });
        }),
        catchError((error: any) => {
          return of(
            getOrderHistoryFailure({
              error:
                error.response && error.response.data
                  ? error.response.data
                  : error.message,
            })
          );
        })
      )
      .subscribe((action) => this.store.dispatch(action));
  }
}
