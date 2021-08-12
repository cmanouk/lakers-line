import React from 'react';

const PlayerSelect = ({ currPlayer, players, setPlayerProfile }) => (
  <div className='player-select'>
    <label htmlFor='player-select'>
      <select
        onChange={(e) => setPlayerProfile(e.target.value)}
        value={currPlayer._id}
        id='player-select'
      >
        {players.map((p) => (
          <option key={`${p.firstName}-${p.lastName}`} value={p._id}>
            {`${p.firstName} ${p.lastName}`}
          </option>
        ))}
      </select>
      <svg version='1.1' width='32' height='32' viewBox='0 0 32 32' fill='#d8d8d8'>
        <path d='M9.914 11.086l-2.829 2.829 8.914 8.914 8.914-8.914-2.828-2.828-6.086 6.086z'></path>
      </svg>
    </label>
  </div>
)

export default PlayerSelect;