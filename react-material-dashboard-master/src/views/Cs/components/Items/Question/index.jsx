import React from 'react';
import Item from '../Item';
import axios from 'axios';

const Question = () => {
  const [value, setValue] = React.useState([]);

  React.useEffect(() => {
    axios.get('http://sungjin5891.cafe24.com/question/list').then(data => {
      setValue(data.data);
    });
  }, []);

  return (
    <React.Fragment>
      {value.map(data => (
        <Item
          key={data.qno}
          title={data.qtitle}
          content={data.qcontent}
          comment={data.qccontent}
          writer={data.qwriter}
        />
      ))}
    </React.Fragment>
  );
};

export default Question;
