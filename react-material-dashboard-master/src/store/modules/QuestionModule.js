import Axios from 'axios';

const COMMENT = 'Question/COMMENT';
const SEARCH_INPUT = 'Question/SEARCH_INPUT';

export const comment = () => ({
  type: COMMENT
});
export const searchInput = input => ({
  type: SEARCH_INPUT,
  input
});

const initialState = {
  input: ''
};

const QuestionModule = (state = initialState, action) => {
  switch (action.type) {
    case 'COMMENT':
      const { content, qno } = action.payload;

      Axios.patch(`http://sungjin5891.cafe24.com/question/update`, {
        qccontent: content,
        qno
      }).catch(err => console.log(err));
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

export default QuestionModule;
