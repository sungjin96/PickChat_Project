import axios from 'axios';

const DELETE = 'Police/DELETE';
const REMOVE = 'Police/REMOVE';

export const delete1 = () => ({ type: DELETE });
export const remove = () => ({ type: REMOVE });

const initialState = {};

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
    default:
      return state;
  }
};

export default PoliceModule;
