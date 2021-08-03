import React from 'react';
import SeasonStats from './SeasonStats';
import StatTrack from './StatTrack';

const PlayerStats = ({ player }) => {
  const { seasonStats, prevTen, prevSeasons } = player;
  const keys = ['PTS', 'REB', 'AST', 'STL', 'BLK'];

  return (
    <section className='player-stats'>
      <SeasonStats stats={seasonStats} keys={keys} />
      <StatTrack
        header='Previous Ten'
        keys={['Game Info', ...keys]}
        stats={prevTen}
        clsName='previous-ten'
      />
      <StatTrack
        header='Previous Seasons'
        keys={['Year/Team', 'GP', ...keys]}
        stats={prevSeasons}
        clsName='previous-seasons'
      />
    </section>
  )
}

export default PlayerStats;