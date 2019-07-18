import axios from 'axios';

const DELETE = 'Police/DELETE';

export const delete1 = () => ({ type: DELETE });

const initialState = {};

const PoliceModule = (state = initialState, action) => {
  switch (action.type) {
    case DELETE:
      const { bpno } = action.payload;
      axios
        .delete(`http://sungjin5891.cafe24.com/board/police/delete/${bpno}`)
        .catch(err => console.log(err));
      return {};

    default:
      return state;
  }
};

export default PoliceModule;
