import React from 'react';

const Header = ({ setPlayerProfile, players, currPlayer }) => {
  return (
    <section className='header'>
      <h1>
        Lakers Line
        <span>The best place to get up-to-date player stats</span>
      </h1>
    </section>
  )
}

export default Header;