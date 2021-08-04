import React from 'react';

const PlayerBioRight = ({ player }) => {
  const { firstName, lastName, jerseyNumber, age, position, height, weight } = player;
  return (
    <div className="player-bio__right">
      <div className="player-bio__right--container">
        <h3>{firstName}<span> {lastName}</span></h3>
        <div className="player-bio__right--info">
          <p>{height}</p>
          <p>{weight}</p>
          <p>{jerseyNumber}</p>
          <p>{position}</p>
          <p>{age}</p>
        </div>
      </div>
    </div>
  )
}

export default PlayerBioRight;