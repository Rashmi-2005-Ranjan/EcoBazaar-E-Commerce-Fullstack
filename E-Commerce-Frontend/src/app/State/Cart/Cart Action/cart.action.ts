import { createAction, props } from '@ngrx/store';

export const addItemToCartRequest = createAction(
  '[Cart] Add Item To Cart Request',
  props<{ requestData: any }>()
);
export const addItemToCartSuccess = createAction(
  '[Cart] Add Item To Cart Success',
  props<{ payload: any }>()
);
export const addItemToCartFailure = createAction(
  '[Cart] Add Item To Cart Failure',
  props<{ error: any }>()
);

export const getCartRequest = createAction('[Cart] Get Cart Request');
export const getCartSuccess = createAction(
  '[Cart] Get Cart Success',
  props<{ payload: any }>()
);
export const getCartFailure = createAction(
  '[Cart] Get Cart Failure',
  props<{ error: any }>()
);

export const removeItemFromCartRequest = createAction(
  '[Cart] Remove Item From Cart Request',
  props<{ requestData: any }>()
);

export const removeItemFromCartSuccess = createAction(
  '[Cart] Remove Item From Cart Success',
  props<{ payload: any }>()
);
export const removeItemFromCartFailure = createAction(
  '[Cart] Remove Item From Cart Failure',
  props<{ error: any }>()
);

export const updateCartItemRequest = createAction(
  '[Cart] Update Cart Item Request',
  props<{ requestData: any }>()
);
export const updateCartItemSuccess = createAction(
  '[Cart] Update Cart Item Success',
  props<{ payload: any }>()
);
export const updateCartItemFailure = createAction(
  '[Cart] Update Cart Item Failure',
  props<{ error: any }>()
);
