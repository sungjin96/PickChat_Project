import Axios from 'axios';

const COMMENT = 'Question/COMMENT';

export const comment = () => ({
  type: COMMENT
});

const initialState = {};

const QuestionModule = (state = initialState, action) => {
  switch (action.type) {
    case 'COMMENT':
      const { content, qno } = action.payload;

      console.log(qno);

      Axios.patch(`http://sungjin5891.cafe24.com/question/update`, {
        qccontent: content,
        qno
      }).catch(err => console.log(err));
      return {};
    default:
      return state;
  }
};

export default QuestionModule;
