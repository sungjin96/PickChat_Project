import React from 'react';
import Insert from './Insert';

const createModal = () => {
  return (
    <div
      style={{
        backgroundColor: '#ffffff',
        position: 'absolute',
        outline: 'none',
        top: '50%',
        left: '50%',
        transform: 'translate(-50%, -50%)',
        width: '60rem',
        height: '50rem'
      }}>
      <Insert />
    </div>
  );
};

export default createModal;
