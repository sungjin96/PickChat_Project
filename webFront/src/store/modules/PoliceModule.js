import axios from 'axios';

const DELETE = 'Police/DELETE';
const REMOVE = 'Police/REMOVE';
const MODAL_CHECK = 'Police/MODAL_CHECK';
const MODAL_CHECK1 = 'Police/MODAL_CHECK1';

export const delete1 = () => ({ type: DELETE });
export const remove = () => ({ type: REMOVE });
export const modalCheck = () => ({ type: MODAL_CHECK });
export const modalCheck1 = () => ({ type: MODAL_CHECK1 });

const initialState = {
  modalState: false,
  bnoo: 0
};

const PoliceModule = (state = initialState, action) => {
  switch (action.type) {
    case DELETE:
      const { bno, bpno } = action.payload;
      axios
        .delete(`http://sungjin5891.cafe24.com/board/bbsdelete/${bno}/${bpno}`)
        .catch(err => console.log(err));
      return {};

    case REMOVE:
      const { no } = action.payload;
      axios
        .delete(`http://sungjin5891.cafe24.com/board/police/delete/${no}`)
        .catch(err => console.log(err));
      return {};
    case MODAL_CHECK:
      const { bnoo } = action.payload;
      console.log(bnoo);
      return {
        modalState: !state.modalState,
        bnoo
      };
    case MODAL_CHECK1:
      return {
        modalState: !state.modalState
      };
    default:
      return state;
  }
};

export default PoliceModule;
