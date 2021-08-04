import React from 'react';
import Select from './Select';

const Header = ({ setPlayerProfile, players, currPlayer }) => {
  return (
    <section className='header'>
      <h1>
        Your Favorite Lakers
        <span>The best place to get up-to-date player stats</span>
      </h1>
    </section>
  )
}

export default Header;