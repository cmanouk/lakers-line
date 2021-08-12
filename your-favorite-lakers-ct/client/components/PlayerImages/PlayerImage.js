import React, { useState } from 'react';

const PlayerImage = ({ url, id }) => {
  const domain = 'https://d20a0vu5jfjkhm.cloudfront.net/images';
  const [ endpoint, setEndpoint ] = useState(url);

  return (
    <div className={`player-img player-img${id}`}>
      <img
        src={`${domain}${endpoint}`}
        alt="player image"
        onError={() => setEndpoint('/LakersLogoReplacement.jpg')}
      />
    </div>
  )
}

export default PlayerImage;