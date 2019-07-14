import axios from 'axios';

const INSERT = 'Notice/INSERT';
const DELETE = 'Notice/DELETE';
const UPDATE = 'Notice/UPDATE';
const SEARCH_INPUT = 'Notice/SEARCH_INPUT';

export const insert = () => ({
  type: INSERT
});
export const delete1 = () => ({ type: DELETE });
export const update = () => ({ type: UPDATE });
export const searchInput = input => ({ type: SEARCH_INPUT });

const initialState = {
  complete: [],
  input: ''
};

const CompleteModule = (state = initialState, action) => {
  switch (action.type) {
    case 'INSERT':
      const { title, content } = action.payload;
      axios
        .post('http://sungjin5891.cafe24.com/notice/insert', { title, content })
        .catch(err => console.log(err));
      return {};

    case 'DELETE':
      const { id } = action;
      axios
        .delete(`http://sungjin5891.cafe24.com/notice/delete/${id}`)
        .catch(err => console.log(err));
      return {};
    case 'UPDATE':
      axios
        .post('http://sungjin5891.cafe24.com/notice/insert', { title, content })
        .catch(err => console.log(err));
      return {};

    case 'SEARCH_INPUT':
      return {
        ...state,
        input: action.payload.input
      };

    default:
      return state;
  }
};

export default CompleteModule;
