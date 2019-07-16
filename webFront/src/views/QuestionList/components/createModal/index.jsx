import React from 'react';
import QuestionContainer from '../../../../containers/QuestionContainer';

const CreateModal = ({ qno }) => {
  const [value, setVaule] = React.useState('');

  return (
    <div
      style={{
        backgroundColor: '#ffffff',
        position: 'absolute',
        outline: 'none',
        top: '50%',
        left: '50%',
        transform: 'translate(-50%, -50%)',
        width: '60rem',
        height: '50rem'
      }}>
      <p>글 번호</p>
      <input value={qno} />
      <p>내용</p>
      <input onChange={e => setVaule(e.target.value)} value={value} />
      <QuestionContainer qno={qno} content={value} />
    </div>
  );
};

export default CreateModal;
