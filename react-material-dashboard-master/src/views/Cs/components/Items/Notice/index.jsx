import React from 'react';
import Item from '../Item';
import axios from 'axios';

const Notice = () => {
  const [value, setValue] = React.useState([]);

  React.useEffect(() => {
    axios.get('http://192.168.0.104:5000/notice/list').then(data => {
      setValue(data.data);
    });
  }, []);

  return (
    <React.Fragment>
      {value.map(data => (
        <Item key={data.nno} title={data.title} content={data.content} />
      ))}
    </React.Fragment>
  );
};

export default Notice;
