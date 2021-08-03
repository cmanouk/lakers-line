import React from 'react';

const Select = ({ currPlayer, players, setPlayerProfile }) => (
  <select
    onChange={(e) => setPlayerProfile(e.target.value)}
    value={currPlayer}
  >
    {players.map((p) => (
      <option key={`${p.firstName}-${p.lastName}`} value={p._id}>
        {`${p.firstName} ${p.lastName}`}
      </option>
    ))}
  </select>
)

export default Select;