import React from 'react';
import Tap from './components/Tap';

import { Main as MainLayout } from 'layouts';

const Cs = () => {
  return (
    <MainLayout>
      <div style={{ margin: '8rem' }}>
        <Tap />
      </div>
    </MainLayout>
  );
};

export default Cs;
