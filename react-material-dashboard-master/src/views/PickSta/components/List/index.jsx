import React from 'react';
import axios from 'axios';

import Grid from '@material-ui/core/Grid';
import Button from '@material-ui/core/Button';

import Cards from '../Cards';

const tags = ['컴퓨터', '개발자', '공부중', '프로그래머'];

const List = () => {
  const [datas, setDatas] = React.useState([]);
  const [value, setVaule] = React.useState(3);

  React.useEffect(() => {
    axios
      .get('http://192.168.0.104:5000/board/bbslist')
      .then(data => setDatas(data.data));
  }, []);

  const getList = () => {
    let bbs = [];
    bbs = datas.slice(0, value);
    return bbs.map(data => (
      <Grid item xs={4} spacing={0}>
        <Cards
          key={data.bno}
          title={data.title}
          content={data.content}
          date={data.regdate}
          writer={data.writer}
          img={data.imgpath}
          tag={tags}
          lcount={241}
          rcount={592}
        />
      </Grid>
    ));
  };

  return (
    <Grid container spacing={3} style={{ width: '90%', margin: 'auto' }}>
      {getList()}
      <Button
        onClick={() => setVaule(value + 3)}
        style={{ width: '100%' }}
        variant="outlined">
        Full
      </Button>
    </Grid>
  );
};

export default List;
