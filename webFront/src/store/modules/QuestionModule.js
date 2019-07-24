import Axios from 'axios';

const COMMENT = 'Question/COMMENT';
const REMOVE = 'Question/REMOVE';
const MODAL_CHECK = 'Question/MODAL_CHECK';

export const comment = () => ({
  type: COMMENT
});
export const remove = () => ({
  type: REMOVE
});
export const modalCheck = () => ({
  type: MODAL_CHECK
});
const initialState = {
  modalState: false,
  updateCheck: false,
  no: 0
};

const QuestionModule = (state = initialState, action) => {
  switch (action.type) {
    case 'COMMENT':
      const { content, qno } = action.payload;
      Axios.patch(`http://sungjin5891.cafe24.com/question/update`, {
        qccontent: content,
        qno
      }).catch(err => console.log(err));
      return {
        modalState: !state.modalState
      };
    case REMOVE:
      const { id } = action.payload;
      Axios.delete(`http://sungjin5891.cafe24.com/question/delete/${id}`).catch(
        err => console.log(err)
      );
      return {};

    case MODAL_CHECK:
      const { no } = action.payload;

      return {
        modalState: !state.modalState,
        updateCheck: !state.updateCheck,
        no: no
      };
    default:
      return state;
  }
};

export default QuestionModule;
