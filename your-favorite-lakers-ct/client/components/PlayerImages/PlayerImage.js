import React from 'react';

const PlayerImage = ({ url, id }) => {

  return (
    <div className={`player-img player-img${id}`}>
      <img src={`./images${url}`} alt="player image" />
    </div>
  )
}

export default PlayerImage;