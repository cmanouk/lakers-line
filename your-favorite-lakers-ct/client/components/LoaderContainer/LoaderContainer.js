import React from 'react';
import Loader from 'react-loader-spinner';

const LoaderContainer = () => (
  <div className='loader-container'>
    <Loader
      type='ThreeDots'
      color='#800080'
      height={120}
      width={120}
    />
  </div>
)

export default LoaderContainer;