import React from 'react';

import './UserCard.css';

const UserCard = ({ img, nick }) => {
  return (
    <React.Fragment>
      <img className="wrap" src={img} alt="사진없음" />
    </React.Fragment>
  );
};

export default UserCard;
