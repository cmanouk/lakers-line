import React from 'react';

const PlayerBioRight = ({ player }) => {
  const { firstName, lastName, jerseyNumber, age, position, height, weight } = player;
  return (
    <div className="player-bio__right">
      <div className="player-bio__right--container">
        <h3>{firstName}<span> {lastName}</span></h3>
        <ul className="player-bio__right--info">
          <li>{height}</li>
          <li>{weight}</li>
          <li>{jerseyNumber}</li>
          <li>{position}</li>
          <li>{age}</li>
        </ul>
      </div>
    </div>
  )
}

export default PlayerBioRight;