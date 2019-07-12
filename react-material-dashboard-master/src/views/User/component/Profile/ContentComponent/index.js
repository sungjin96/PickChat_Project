import React from 'react';

import './style.css';
import Container from '../../Container';
import Tap from './Tap';

const ContentComponent = ({ id }) => {
  return (
    <Container>
      <Tap id={id} />
    </Container>
  );
};

export default ContentComponent;
