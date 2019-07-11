import React from 'react';
import Axios from 'axios';
import ProfileContent from './ProfileComponent/ProfileContent';
import Container from '../Container';

import './style.css';

const Profile = ({ id }) => {
  const [imgPath, setImgPath] = React.useState('');
  React.useEffect(() => {
    Axios.get(`http://192.168.0.26/user/read/${id}`).then(result =>
      setImgPath(result.data.soloimg)
    );
  }, []);

  return (
    <Container>
      <div className="imgwrap">
        <img src={imgPath} alt="프로필" className="img" />
      </div>
      <div className="profileContent">
        <ProfileContent id={id} />
      </div>
    </Container>
  );
};

export default Profile;
