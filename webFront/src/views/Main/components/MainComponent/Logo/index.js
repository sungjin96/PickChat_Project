import React from 'react';
import logo from '../../../../../assets/img/download_btn.png';

import './Logo.css';

const Logo = () => {
  return (
    <a className="logo-wrap" href="https://play.google.com/store/apps?utm_source=apac_med&utm_medium=hasem&utm_content=Jul1519&utm_campaign=Evergreen&pcampaignid=MKT-DR-apac-kr-1003227-med-hasem-ap-Evergreen-Jul1519-Text_Search_BKWS-BKWS%7cONSEM_kwid_43700045102985476_creativeid_268791854618_device_c_kwd_kwd-48979542051_geoid_1009871_network_g&gclid=EAIaIQobChMItP2krOfO4wIVg4BwCh1_QgcJEAAYASAAEgJ_uvD_BwE&gclsrc=aw.ds">
      <img src={logo} alt="다운로드" className="logo-img" />
      <div className="logo-title">DOWNLOAD FROM PLAYSTORE</div>
    </a>
  );
};

export default Logo;
