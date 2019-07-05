import React from 'react';
import Item from '../Item';

const datas = [
  {
    id: 0,
    title: '제목 1',
    content: '내용 1',
    comment: '댓글 1'
  },
  {
    id: 1,
    title: '제목 2',
    content: '내용 2',
    comment: '댓글 2'
  },
  {
    id: 2,
    title: '제목 3',
    content: '내용 3',
    comment: '댓글 3'
  },
  {
    id: 3,
    title: '제목 4',
    content: '내용 4',
    comment: '댓글 4'
  },
  {
    id: 4,
    title: '제목 5',
    content: '내용 5',
    comment: '댓글 5'
  }
];

const FAQs = () => {
  return (
    <React.Fragment>
      {datas.map(data => (
        <Item
          key={data.id}
          title={data.title}
          content={data.content}
          comment={data.comment}
        />
      ))}
    </React.Fragment>
  );
};

export default FAQs;
