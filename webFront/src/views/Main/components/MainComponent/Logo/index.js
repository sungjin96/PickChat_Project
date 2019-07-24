import React from 'react';
import logo from '../../../../../assets/img/download_btn.png';

import './Logo.css';

const Logo = () => {
  return (
    <a className="logo-wrap" href="https://naver.com">
      <img src={logo} alt="다운로드" className="logo-img" />
      <div className="logo-title">DOWNLOAD FROM PALYSTORE</div>
    </a>
  );
};

export default Logo;
