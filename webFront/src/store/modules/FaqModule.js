import axios from 'axios';

const SEARCH_INPUT = 'Faq/SEARCH_INPUT';

export const searchInput = () => ({ type: SEARCH_INPUT });

const initialState = {
  input: ''
};

const FaqModule = (state = initialState, action) => {
  switch (action.type) {
    case SEARCH_INPUT:
      return {
        ...state,
        input: action.payload.input
      };
    default:
      return state;
  }
};

export default FaqModule;
