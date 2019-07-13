import Axios from 'axios';

const SEARCH_INPUT = 'Pick/SEARCH_INPUT';

export const searchInput = input => ({
  type: SEARCH_INPUT,
  input
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
    default:
      return state;
  }
};

export default QuestionModule;
