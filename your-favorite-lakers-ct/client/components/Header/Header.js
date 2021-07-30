import React from 'react';

const Header = ({ setPlayerProfile, currPlayer }) => {

  return (
    <div className='header'>
      <h1>
        Your Favorite LAkers:
        <span>The easiest way to get your favorite players' stats!</span>
      </h1>
      <button onClick={() => setPlayerProfile('1966')}>
        Send it!
      </button>
    </div>
  )
}

export default Header;