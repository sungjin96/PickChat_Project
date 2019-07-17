import axios from 'axios';

const INSERT = 'Pick/INSERT';
const DELETE = 'Pick/DELETE';
const SEARCH_INPUT = 'Pick/SEARCH_INPUT';

export const insert = () => ({
  type: INSERT
});
export const delete1 = () => ({ type: DELETE });
export const searchInput = () => ({ type: SEARCH_INPUT });

const initialState = {
  complete: [],
  input: ''
};

const PickModule = (state = initialState, action) => {
  switch (action.type) {
    case DELETE:
      const { id } = action.payload;
      axios
        .delete(`http://sungjin5891.cafe24.com/board/bbsdelete/${id}`)
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

export default PickModule;
