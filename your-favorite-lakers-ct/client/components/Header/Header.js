import React from 'react';

const Header = ({ setPlayerProfile, players, currPlayer }) => {
  return (
    <section className='header'>
      <div className="header__background"></div>
      <div className="header__text">
        <h1>
          Lakers Line
        </h1>
        <p>The best place to get up-to-date player stats</p>
      </div>
    </section>
  )
}

export default Header;