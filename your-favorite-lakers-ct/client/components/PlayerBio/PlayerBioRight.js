import React from 'react';

const PlayerBioRight = ({ player }) => {
  const { firstName, lastName, jerseyNumber,
        age, position, height, weight } = player;

  return (
    <div className="player-bio__right">
      <div className="player-bio__right--container">
        <h3>{firstName}<span> {lastName}</span></h3>
        <ul className="player-bio__right--info">
          <li><span>Height</span> {height}</li>
          <li><span>Weight</span> {weight}</li>
          <li><span>Jersey</span> {jerseyNumber}</li>
          <li><span>Position</span> {position}</li>
          <li><span>Age</span> {age}</li>
        </ul>
      </div>
    </div>
  )
}

export default PlayerBioRight;