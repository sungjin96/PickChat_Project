import Axios from 'axios';

const SEARCH_INPUT = 'Pick/SEARCH_INPUT';
const INSERT = 'Pick/INSERT';
const REMOVE = 'Pick/REMOVE';

export const searchInput = input => ({
  type: SEARCH_INPUT,
  input
});
export const insert = () => ({
  type: INSERT
});
export const remove = () => ({
  type: REMOVE
});

const initialState = {
  input: ''
};

const QuestionModule = (state = initialState, action) => {
  switch (action.type) {
    case 'SEARCH_INPUT':
      return {
        ...state,
        input: action.payload.input
      };
    case 'INSERT':
      Axios.post();
      return {};
    case 'REMOVE':
      const bno = action.payload.bno;
      Axios.delete(`http://sungjin5891.cafe24.com/board/bbsdelete/${bno}`);
      return {};
    default:
      return state;
  }
};

export default QuestionModule;
