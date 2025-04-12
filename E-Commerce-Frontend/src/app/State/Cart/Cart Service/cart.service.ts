import { Injectable } from '@angular/core';
import { BASE_API_URL } from '../../../Config/api';
import { Store } from '@ngrx/store';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { catchError, map, of } from 'rxjs';
import {
  addItemToCartFailure,
  addItemToCartSuccess,
  getCartFailure,
  getCartSuccess,
  removeItemFromCartSuccess,
} from '../Cart Action/cart.action';

@Injectable({
  providedIn: 'root',
})
export class CartService {
  API_BASE_URL = BASE_API_URL;
  constructor(
    private store: Store,
    private http: HttpClient,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  addItemToCart(reqData: any) {
    const url = `${this.API_BASE_URL}/api/cart/add`;
    const headers = new HttpHeaders({
      Authorization: `Bearer ${localStorage.getItem('user')}`,
      'Content-Type': 'application/json',
    });
    return this.http
      .put(url, reqData, { headers })
      .pipe(
        map((data: any) => {
          console.log(`Added Data ${data}`);
          return addItemToCartSuccess({ payload: data });
        }),
        catchError((error: any) => {
          return of(
            addItemToCartFailure(
              error.response && error.response.data
                ? error.response.data
                : error.message
            )
          );
        })
      )
      .subscribe((action) => this.store.dispatch(action));
  }

  getCart() {
    const url = `${this.API_BASE_URL}/api/cart`;
    const headers = new HttpHeaders({
      Authorization: `Bearer ${localStorage.getItem('user')}`,
      'Content-Type': 'application/json',
    });
    return this.http
      .get(url, { headers })
      .pipe(
        map((data: any) => {
          console.log(`Data ${data}`);
          return getCartSuccess({ payload: data });
        }),
        catchError((error: any) => {
          return of(
            getCartFailure(
              error.response && error.response.data
                ? error.response.data
                : error.message
            )
          );
        })
      )
      .subscribe((action) => this.store.dispatch(action));
  }

  removeCartItem(cartItemId:number){
    const url = `${this.API_BASE_URL}/api/cart_items/${cartItemId}`;
    const headers = new HttpHeaders({
      Authorization: `Bearer ${localStorage.getItem('user')}`,
      'Content-Type': 'application/json',
    });
    return this.http
      .delete(url, { headers })
      .pipe(
        map((data: any) => {
          console.log(`Data ${data}`);
          return removeItemFromCartSuccess({ payload: data });
        }),
        catchError((error: any) => {
          return of(
            getCartFailure(
              error.response && error.response.data
                ? error.response.data
                : error.message
            )
          );
        })
      )
      .subscribe((action) => this.store.dispatch(action));
  } 
}
