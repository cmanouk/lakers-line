import React from 'react';

const SeasonStats = ({ stats, keys }) => (
  <div className='season-stats'>
    <h6 className={'season-stats__header'}>Season Stats</h6>
    <ul className={'season-stats__stats'}>
      {keys.map((k) =>
      <li key={k} className='season-stats__stats--stats'>
        <div className='stat-attr'>{k}</div>
        <div className='stat-val'>{stats[k]}</div>
      </li>)}
    </ul>
  </div>
)

export default SeasonStats;