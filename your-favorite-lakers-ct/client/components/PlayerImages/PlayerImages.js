import React from 'react';
import PlayerImage from './PlayerImage';

const PlayerImages = ({ urls }) => {
  return (
    <div className="player-imgs">
      <div className="player-imgs__container">
        {urls.map((url, i) => i ? <PlayerImage url={url} key={url} id={i} /> : null)}
      </div>
    </div>
  )
}

export default PlayerImages;