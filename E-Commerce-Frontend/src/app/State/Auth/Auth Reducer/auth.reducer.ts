//* AUTH Reducer

import { createReducer, on } from '@ngrx/store';
import { login, loginFailure, loginSuccess, register, registerFailure, registerSuccess } from '../Auth Action/auth.action';
const initialState = {
  user: null,
  loading: false,
  error: null,
};

export const authReducer = createReducer(
  initialState,
  on(login, (state) => ({ ...state, loading: true, error: null })),
  on(loginSuccess, (state, { user }) => ({
    ...state,
    loading: false,
    error: null,
    user,
  })),
  on(loginFailure, (state, { error }) => ({
    ...state,
    loading: true,
    error: error,
  })),





  //* Register Reducers


  on(register, (state) => ({ ...state, loading: true, error: null })),
  on(registerSuccess, (state, { user }) => ({
    ...state,
    loading: false,
    error: null,
    user: state.user,
  })),
  on(registerFailure, (state, { error }) => ({
    ...state,
    loading: true,
    error: error,
  }))
);
