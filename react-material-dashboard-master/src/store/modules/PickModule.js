import Axios from 'axios';

const SEARCH_INPUT = 'Pick/SEARCH_INPUT';
const INSERT = 'Pick/INSERT';

export const searchInput = input => ({
  type: SEARCH_INPUT,
  input
});
export const insert = () => ({
  type: INSERT
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
    default:
      return state;
  }
};

export default QuestionModule;
