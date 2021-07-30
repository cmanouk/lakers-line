import React from 'react';

const PlayerBioRight = ({ player }) => {
  const { firstName, lastName, jerseyNumber, age, position } = player;
  return (
    <div className="player-bio__right">
      <div className="player-bio__right--container">
        <h2>{firstName} <span>{lastName}</span></h2>
        <div className="player-bio__right--info">
          <p>{jerseyNumber}</p>
          <p>{position}</p>
          <p>{age}</p>
        </div>
      </div>
    </div>
  )
}

export default PlayerBioRight;