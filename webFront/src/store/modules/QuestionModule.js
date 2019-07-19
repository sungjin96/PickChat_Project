import Axios from 'axios';

const COMMENT = 'Question/COMMENT';
const REMOVE = 'Question/REMOVE';
const TEST = 'Question/TEST';

export const comment = () => ({
  type: COMMENT
});
export const remove = () => ({
  type: REMOVE
});
export const Test = () => ({
  type: TEST
});
const initialState = {
  CloseState: false,
  UpdateCheak: false
};

const QuestionModule = (state = initialState, action) => {
  switch (action.type) {
    case 'COMMENT':
      console.log(state.CloseState);
      const { content, qno } = action.payload;
      Axios.patch(`http://sungjin5891.cafe24.com/question/update`, {
        qccontent: content,
        qno
      }).catch(err => console.log(err));
      return {
        ...state,
        CloseState: false,
        UpdateCheak: !state.UpdateCheak
      };

    case TEST:
      return {
        ...state,
        CloseState: !state.CloseState
      };
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
