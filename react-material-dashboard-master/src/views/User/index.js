import React from 'react';
import Profile from './component/Profile';
import ContentComponent from './component/Profile/ContentComponent';
import Dashboard from 'layouts/Dashboard';

const User = ({ match }) => {
  const { id } = match.params;

  return (
    <Dashboard>
      <div className="profile">
        <Profile id={id} />
      </div>
      <div className="content">
        <ContentComponent id={id} />
      </div>
    </Dashboard>
  );
};

export default User;
