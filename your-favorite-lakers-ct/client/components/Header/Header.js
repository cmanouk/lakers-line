import React from 'react';
import Select from './Select';

const Header = ({ setPlayerProfile, players, currPlayer }) => {
  return (
    <div className='header'>
      <h1>
        Your Favorite Lakers: <span>The easiest way to get up-to-date players' stats!</span>
      </h1>
      <Select
        setPlayerProfile={setPlayerProfile}
        players={players}
        currPlayer={currPlayer}
      />
    </div>
  )
}

export default Header;