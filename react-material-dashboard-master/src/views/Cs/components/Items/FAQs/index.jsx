import React from 'react';
import Item from '../Item';
import Faq from '../../../../../data/Faq';

const FAQs = () => {
  return (
    <React.Fragment>
      {Faq.map(data => (
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
