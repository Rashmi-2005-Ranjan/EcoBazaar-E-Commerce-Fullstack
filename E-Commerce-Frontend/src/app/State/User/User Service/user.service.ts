import { HttpClient, HttpHeaders } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Store } from '@ngrx/store';
import { BASE_API_URL } from '../../../Config/api';
import { catchError, map, of } from 'rxjs';
import { getUserProfileFailure, getUserProfileSuccess, logoutSuccess } from '../User Action/user.action';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  private http = inject(HttpClient);
  private store = inject(Store);
  private apiUrl = BASE_API_URL + 'api';

  getUserProfile() {
    // Get user data from localStorage
    const userData = localStorage.getItem('user');
    if (!userData) {
      this.store.dispatch(getUserProfileFailure({ error: 'No authentication data found' }));
      return of(); // Return empty observable
    }

    try {
      // Parse user object and extract JWT
      const user = JSON.parse(userData);
      const jwt = user?.jwt;

      if (!jwt) {
        throw new Error('JWT token missing in user data');
      }

      // Create headers with fresh JWT
      const headers = new HttpHeaders({
        'Authorization': `Bearer ${jwt}`
      });

      return this.http.get(`${this.apiUrl}/users/profile`, { headers }).pipe(
        map((userProfile: any) => {
          console.log('User Profile Success:', userProfile);
          return getUserProfileSuccess({ userProfile });
        }),
        catchError((error) => {
          // Handle Angular HttpClient error format
          const errorMessage = error.error?.message || 
                             error.message || 
                             'Unknown error occurred';
          console.error('Profile Error:', errorMessage);
          return of(getUserProfileFailure({ error: errorMessage }));
        })
      ).subscribe((action) => this.store.dispatch(action));
    } catch (error) {
      console.error('LocalStorage parsing error:', error);
      this.store.dispatch(getUserProfileFailure({ 
        error: 'Invalid authentication data format' 
      }));
      return of();
    }
  }

  logout(){
    localStorage.removeItem('user');
    this.store.dispatch(logoutSuccess())
  }
}