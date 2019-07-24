import React from 'react';
import axios from 'axios';
import Cards from '../../../PickSta/components/Cards';
import { useSelector } from 'react-redux';

const CreateModal = React.memo(() => {
  const GetData = () => {
    const [data, setData] = React.useState([]);
    const [bnoo] = useSelector(state => [state.PoliceModule.bnoo], []);
    React.useEffect(() => {
      axios
        .get(`http://sungjin5891.cafe24.com/board/bbsread/${bnoo}`)
        .then(result => {
          setData(result.data);
        });
    }, []);
    if (data.length !== 0) {
      return (
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
      );
    }
  };

  return (
    <div
      style={{
        backgroundColor: 'rgba(255,255,255,0)',
        position: 'absolute',
        outline: 'none',
        top: '50%',
        left: '50%',
        transform: 'translate(-50%, -50%)',
        width: '60rem',
        height: '50rem'
      }}>
      {GetData()}
    </div>
  );
});

export default CreateModal;
