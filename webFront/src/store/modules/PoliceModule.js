import axios from 'axios';

const DELETE = 'Police/DELETE';
const REMOVE = 'Police/REMOVE';

export const delete1 = () => ({ type: DELETE });
export const remove = () => ({ type: REMOVE});

const initialState = {};

const PoliceModule = (state = initialState, action) => {
  switch (action.type) {
    case DELETE:
      const {  id, bpno } = action.payload;
        axios
        .delete(`http://sungjin5891.cafe24.com/board/bbsdelete/${id}/${bpno}`)
        .catch(err => console.log(err));
      return {};

      case REMOVE: 
      const { bpno1 } = action.payload;
      axios
      .delete(`http://sungjin5891.cafe24.com/board/police/delete/${bpno1}`)
      .catch(err => console.log(err));
        return{};

    default:
      return state;
  }
};

export default PoliceModule;
