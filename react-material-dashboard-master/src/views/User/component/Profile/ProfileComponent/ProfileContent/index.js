import React from 'react';
import Axios from 'axios';

import './style.css';

const ProfileContent = ({ id }) => {
  const [user, setUser] = React.useState('');
  const [likeCounter, setLikeCounter] = React.useState(0);
  const [bbsCounter, setBbsCOunter] = React.useState(0);
  React.useEffect(() => {
    Axios.get(`http://192.168.0.26/user/read/${id}`).then(result =>
      setUser(result.data)
    );

    Axios.get(`http://192.168.0.26/user/list_likereceiver/${id}`).then(result =>
      setLikeCounter(result.data.length)
    );
  }, []);

  const getBoardCounter = () => {
    Axios.get(
      `http://192.168.0.104:5000/board/userbbslist/${user.usernickname}`
    ).then(result => setBbsCOunter(result.data.length));
  };

  getBoardCounter();

  return (
    <React.Fragment>
      <div className="userid">
        <span style={{ fontSize: '2rem' }}>
          {user.usernickname !== null ? user.usernickname : 'Lodding'}
        </span>
        <span>
          <button>버튼1</button>
        </span>
        <span>
          <button>버튼2</button>
        </span>
      </div>
      <div className="count">
        <span>
          <b style={{ fontSize: '1.5em' }}>
            {bbsCounter !== 0 ? bbsCounter : '0'}
          </b>
          게시글
        </span>
        <span>
          <b style={{ fontSize: '1.5rem' }}>
            {likeCounter !== 0 ? likeCounter : '0'}
          </b>
          좋아요받은수
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
