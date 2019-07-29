import React from 'react';
import logo from '../../../../../assets/img/download_btn.png';

import './Logo.css';

const Logo = () => {
  return (
    <a className="logo-wrap" href="https://play.google.com/store/apps/details?id=com.pickchat_go.login">
      <img src={logo} alt="다운로드" className="logo-img" />
      <div className="logo-title">DOWNLOAD FROM PLAYSTORE</div>
    </a>
  );
};

export default Logo;
