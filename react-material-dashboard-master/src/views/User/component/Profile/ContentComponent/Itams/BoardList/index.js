import React from 'react';
import Cards from '../../../../../../PickSta/components/Cards';
import Grid from '@material-ui/core/Grid';
import Button from '@material-ui/core/Button';

const BoardList = ({ data }) => {
  const [value, setVaule] = React.useState(3);

  const getList = () => {
    let bbs = [];
    bbs = data.slice(0, value);
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
        />
      </Grid>
    ));
  };

  return (
    <Grid container spacing={3} style={{ width: '90%', margin: 'auto' }}>
      {getList()}
      {data.length === 0 ? (
        '데이터가 없습니다.'
      ) : (
        <Button
          onClick={() => setVaule(value + 3)}
          style={{ width: '100%' }}
          variant="outlined">
          Full
        </Button>
      )}
    </Grid>
  );
};

export default BoardList;
