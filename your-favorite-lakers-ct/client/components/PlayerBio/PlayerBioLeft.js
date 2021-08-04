import React from 'react';

const PlayerBioLeft = ({ firstName, lastName, url }) => (
  <div className="player-bio__left">
    <div className="player-bio__img--container">
        <img
          src={`./images${url}`}
          alt={`${firstName} ${lastName} profile picture`}
          className="player-bio__img"
        />
    </div>
  </div>
)

export default PlayerBioLeft;