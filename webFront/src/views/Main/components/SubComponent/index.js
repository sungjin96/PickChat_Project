import React from 'react';
import axios from 'axios';
import UserCard from './UserCard';
import './style.css';

const SubComponent = ({ content, content2 }) => {
  const [users, setUsers] = React.useState([]);

  React.useEffect(() => {
    axios
      .get('http://sungjin5891.cafe24.com/user/list_user')
      .then(result => {
        setUsers(result.data);
      })
      .catch(err => {});
    return () => {};
  }, []);

  return (
    <div style={style.container}>
      <div style={{ color: 'black' }}>
        <div>
          <h1>{content}</h1>
        </div>
        <div style={{ margin: '20px', color: '#5D5D5D' }}>
          <h2>{content2}</h2>
        </div>
        <div className="Wrap">
          {users.slice(0, 6).map(data => (
            <UserCard img={data.soloimg} nick={data.usernickname} />
          ))}
        </div>
      </div>
    </div>
  );
};

const style = {
  container: {
    width: '100%',
    height: '700px',
    paddingTop: '80px',
    backgroundColor: '#ffffff',
    textAlign: 'center'
  }
};

export default SubComponent;
