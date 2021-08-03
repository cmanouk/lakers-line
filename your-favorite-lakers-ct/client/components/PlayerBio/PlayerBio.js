import React from 'react';
import PlayerBioLeft from './PlayerBioLeft';
import PlayerBioRight from './PlayerBioRight';

const PlayerBio = ({ player }) => {
  const { firstName, lastName, urls } = player;
  return (
    <section className='player-bio'>
      <PlayerBioLeft firstName={firstName} lastName={lastName} url={urls[0]} />
      <PlayerBioRight player={player} />
    </section>
  )
}

export default PlayerBio;