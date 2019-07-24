import Axios from 'axios';

const COMMENT = 'Question/COMMENT';
const REMOVE = 'Question/REMOVE';

export const comment = () => ({
  type: COMMENT
});
export const remove = () => ({
  type: REMOVE
});
const initialState = {};

const QuestionModule = (state = initialState, action) => {
  switch (action.type) {
    case 'COMMENT':
      const { content, qno } = action.payload;
      Axios.patch(`http://sungjin5891.cafe24.com/question/update`, {
        qccontent: content,
        qno
      }).catch(err => console.log(err));
      return {};
    case REMOVE:
      const { id } = action.payload;
      Axios.delete(`http://sungjin5891.cafe24.com/question/delete/${id}`).catch(
        err => console.log(err)
      );
      return {};
    default:
      return state;
  }
};

export default QuestionModule;
