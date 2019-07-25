import React from 'react';
import axios from 'axios';

import Grid from '@material-ui/core/Grid';
import Button from '@material-ui/core/Button';

import Cards from '../Cards';

const List = () => {
  const [datas, setDatas] = React.useState([]);
  const [value, setVaule] = React.useState(6);

  React.useEffect(() => {
    var headers = {
      'Access-Control-Allow-Origin': '*',
      Accept: 'application/json',
      'Content-Type': 'application/json'
    };

    axios
      .get('http://sungjin5891.cafe24.com/board/bbslist', headers)
      .then(data => setDatas(data.data));
  }, []);

  const getList = () => {
    let bbs = [];
    bbs = datas.slice(0, value);
    return bbs.map(data => (
      <Grid item xs={4} spacing={0}>
        <Cards
          key={data.bno}
          id={data.bno}
          title={data.title}
          content={data.content}
          date={data.regdate}
          writer={data.writer}
          img={data.imgpath}
          tag={data.tagword}
          soloimg={data.soloimg}
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
        onClick={() => setVaule(value + 6)}
        style={{ width: '100%' }}
        variant="outlined">
        Full
      </Button>
    </Grid>
  );
};

export default List;
