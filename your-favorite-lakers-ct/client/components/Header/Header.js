import React from 'react';

const Header = ({ setPlayerProfile, players, currPlayer }) => {
  return (
    <div className='header'>
      <h1>
        Your Favorite Lakers: <span>The easiest way to get up-to-date players' stats!</span>
      </h1>
      <select
        onChange={(e) => setPlayerProfile(e.target.value)}
        value={currPlayer}
      >
        {players.map((p, i) => (
          <option key={`${p.firstName}-${i}`} value={p._id}>
            {`${p.firstName} ${p.lastName}`}
          </option>
        ))}
      </select>
    </div>
  )
}

export default Header;