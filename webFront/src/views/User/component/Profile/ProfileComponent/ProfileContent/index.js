import React from 'react';
import Axios from 'axios';

import './style.css';

const ProfileContent = ({ id }) => {
  const [user, setUser] = React.useState('');
  const [likeCounter, setLikeCounter] = React.useState(0);
  const [bbsCounter, setBbsCOunter] = React.useState(0);
  React.useEffect(() => {
    Axios.get(`http://sungjin5891.cafe24.com/user/read/${id}`).then(result =>
      setUser(result.data)
    );

    Axios.get(
      `http://sungjin5891.cafe24.com/user/list_likereceiver/${id}`
    ).then(result => setLikeCounter(result.data.length));
  }, []);

  const getBoardCounter = () => {
    Axios.get(`http://sungjin5891.cafe24.com/board/userbbslist/${id}`).then(
      result => setBbsCOunter(result.data.length)
    );
  };

  getBoardCounter();

  return (
    <React.Fragment>
      <div className="userid">
        <span style={{ fontSize: '2rem' }}>
          {user.usernickname !== null ? user.usernickname : 'Lodding'}
        </span>
      </div>
      <div className="count">
        <span>
          게시글{' '}
          <b style={{ fontSize: '1.5em' }}>
            {bbsCounter !== 0 ? bbsCounter : '0'}
          </b>
        </span>
        <span>
          좋아요받은수{' '}
          <b style={{ fontSize: '1.5rem' }}>
            {likeCounter !== 0 ? likeCounter : '0'}
          </b>
        </span>
      </div>
      <div className="content">
        <p>
          <b style={{ fontSize: '1.2em' }}>
            {user.username !== null ? user.username : 'Lodding'}
          </b>
        </p>
        <p>{user.localname !== null ? user.localname : 'Lodding'}</p>
        <p>{user.usercomment !== null ? user.usercomment : 'Lodding'}</p>
      </div>
    </React.Fragment>
  );
};

export default ProfileContent;
