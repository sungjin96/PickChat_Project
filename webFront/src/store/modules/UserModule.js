import axios from 'axios';

const INSERT = 'User/INSERT';
const DELETE = 'User/DELETE';
const SEARCH_INPUT = 'User/SEARCH_INPUT';

export const insert = () => ({
  type: INSERT
});
export const delete1 = () => ({ type: DELETE });
export const searchInput = () => ({ type: SEARCH_INPUT });

const initialState = {};

const UserModule = (state = initialState, action) => {
  switch (action.type) {
    default:
      return state;
  }
};

export default UserModule;
