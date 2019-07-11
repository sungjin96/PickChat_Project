import axios from 'axios';

const INSERT = 'Notice/INSERT';
const DELETE = 'Notice/DELETE';
const UPDATE = 'Notice/UPDATE';
const CHECKED = 'Notice/CHECKED';
const CHECK_ALL = 'Notice/CHECK_ALL';

export const insert = () => ({
  type: INSERT
});
export const checked = () => ({ type: CHECKED });
export const checkAll = () => ({ type: CHECK_ALL });
export const delete1 = () => ({ type: DELETE });
export const update = () => ({ type: UPDATE });

const initialState = {
  complete: []
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

    default:
      return state;
  }
};

export default CompleteModule;
