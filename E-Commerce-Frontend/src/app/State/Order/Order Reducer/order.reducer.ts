import { createReducer, on } from '@ngrx/store';
import * as OrderActions from '../Order Action/order.action';

export interface OrderState {
  loading: boolean;
  error: string | null;
  order: any | null;
  orders: any[];
}

export const initialState: OrderState = {
  loading: false,
  error: null,
  order: null,
  orders: [],
};

export const orderReducer = createReducer(
  initialState,
  on(OrderActions.createOrderRequest, (state) => ({
    ...state,
    loading: true,
    error: null,
  })),
  on(OrderActions.createOrderSuccess, (state, { payload }) => ({
    ...state,
    loading: false,
    error: null,
    order: payload.order,
  })),
  on(OrderActions.createOrderFailure, (state, { error }) => ({
    ...state,
    loading: false,
    error: error,
  })),
  on(OrderActions.getOrderByIdRequest, (state) => ({
    ...state,
    loading: true,
    error: null,
  })),
  on(OrderActions.getOrderByIdSuccess, (state, { order }) => ({
    ...state,
    loading: false,
    error: null,
    order: order,
  })),
  on(OrderActions.getOrderByIdFailure, (state, { error }) => ({
    ...state,
    loading: false,
    error: error,
  })),
  on(OrderActions.getOrderHistoryRequest, (state) => ({
    ...state,
    loading: true,
    error: null,
  })),
  on(OrderActions.getOrderHistorySuccess, (state, { orders }) => ({
    ...state,
    loading: false,
    error: null,
    orders: orders,
  })),
  on(OrderActions.getOrderHistoryFailure, (state, { error }) => ({
    ...state,
    loading: false,
    error: error,
  })),
);
