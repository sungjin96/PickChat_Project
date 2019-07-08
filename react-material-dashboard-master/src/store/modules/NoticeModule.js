import axios from 'axios';

const INSERT = 'INSERT';
const DELETE = 'DELETE';
const UPDATE = 'UPDATE';
const CHECKED = 'CHECKED';
const CHECK_ALL = 'CHECK_ALL';

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
        .post('http://192.168.0.104:5000/notice/insert', { title, content })
        .catch(err => console.log(err));
      return {};

    case 'DELETE':
      const { id } = action;
      axios
        .delete(`http://192.168.0.104:5000/notice/delete/${id}`)
        .catch(err => console.log(err));
      return {};
    case 'UPDATE':
      axios
        .post('http://192.168.0.104:5000/notice/insert', { title, content })
        .catch(err => console.log(err));
      return {};

    default:
      return state;
  }
};

export default CompleteModule;
