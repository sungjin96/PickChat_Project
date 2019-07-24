import axios from 'axios';

const INSERT = 'Notice/INSERT';
const DELETE = 'Notice/DELETE';
const SEARCH_INPUT = 'Notice/SEARCH_INPUT';
const MODAL_CHECK = 'Notice/MODAL_CHECK';

export const insert = () => ({
  type: INSERT
});
export const delete1 = () => ({ type: DELETE });
export const searchInput = () => ({ type: SEARCH_INPUT });
export const modalCheck = () => ({ type: MODAL_CHECK });

const initialState = {
  complete: [],
  input: '',
  modalState: false
};

const CompleteModule = (state = initialState, action) => {
  switch (action.type) {
    case 'INSERT':
      const { title, content } = action.payload;
      axios
        .post('http://sungjin5891.cafe24.com/notice/insert', { title, content })
        .catch(err => console.log(err));
      return {
        ...state,
        modalState: !state.modalState
      };

    case 'DELETE':
      const { id } = action;
      axios
        .delete(`http://sungjin5891.cafe24.com/notice/delete/${id}`)
        .catch(err => console.log(err));
      return {};

    case 'SEARCH_INPUT':
      return {
        ...state,
        input: action.payload.input
      };

    case MODAL_CHECK:
      return {
        ...state,
        modalState: !state.modalState
      };

    default:
      return state;
  }
};

export default CompleteModule;
