import React from 'react';

const PlayerBioLeft = ({ firstName, lastName, url }) => (
  <div className="player-bio__left">
    <img
      src={`https://d20a0vu5jfjkhm.cloudfront.net/images${url}`}
      alt={`${firstName} ${lastName} profile picture`}
      className="player-bio__left--img"
    />
  </div>
)

export default PlayerBioLeft;