import React from 'react';

const BlockList = ({ userid }) => {
  return <div>{userid === undefined ? '데이터가 없습니다.' : userid}</div>;
};

export default BlockList;
