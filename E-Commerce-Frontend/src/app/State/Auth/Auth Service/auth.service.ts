import { inject, Injectable } from '@angular/core';
import { BASE_API_URL } from '../../../Config/api';
import { HttpClient } from '@angular/common/http';
import { Store } from '@ngrx/store';
import { catchError, map, of } from 'rxjs';
import { loginFailure, loginSuccess, registerFailure, registerSuccess } from '../Auth Action/auth.action';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private http = inject(HttpClient);
  private store = inject(Store);
  private apiUrl = BASE_API_URL + 'auth';

  login(loginData: any) {
    return this.http
      .post(`${this.apiUrl}/signin`, loginData)
      .pipe(
        map((user: any) => {
          console.log('Login User:', user);
          if (user.jwt) {
            localStorage.setItem('user', JSON.stringify(user));
            return loginSuccess({ user }); // ✅ Correct action
          } else {
            return loginFailure({ error: 'JWT missing in response' }); // ✅ Handle invalid response
          }
        }),
        catchError((error: any) => {
          return of(
            loginFailure(
              error.response?.data?.message || error.message || 'Login failed'
            )
          );
        })
      )
      .subscribe((action) => this.store.dispatch(action));
  }

  register(registerData: any) {
    return this.http
      .post(`${this.apiUrl}/signup`, registerData)
      .pipe(
        map((user: any) => {
          console.log('Sign Up User:', user);
          if (user.jwt) {
            localStorage.setItem('user', JSON.stringify(user));
            return registerSuccess({ user }); // ✅ Correct action
          } else {
            return registerFailure({ error: 'JWT missing in response' }); // ✅ Handle invalid response
          }
        }),
        catchError((error: any) => {
          return of(
            registerFailure(
              error.response?.data?.message || error.message || 'Registration failed'
            )
          );
        })
      )
      .subscribe((action) => this.store.dispatch(action));
  }
}