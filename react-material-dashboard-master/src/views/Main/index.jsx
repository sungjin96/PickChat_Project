import React from 'react';
import CssBaseline from '@material-ui/core/CssBaseline';
import Typography from '@material-ui/core/Typography';
import Container from '@material-ui/core/Container';
import { Main as MainLayout } from 'layouts';
import SubComponent from './components/SubComponent'

import './style.css'

const Main = () => {

  return (
    <MainLayout>
      <React.Fragment>
        <CssBaseline />        
          <div className="test" />
        <SubComponent content='글램에서 회원님의 매력을 평가 받아보세요.' content2='asdfasdf' img='#'/>
        <SubComponent content='함께함의 가치를 믿습니다.' img='#'/>
      </React.Fragment>
    </MainLayout>
  );
};

export default Main;
