import React from 'react';
import SelectBar from '../SelectBar/SelectBar';
import PlayerBio from '../PlayerBio/PlayerBio';
import PlayerImages from '../PlayerImages/PlayerImages';
import PlayerStats from '../PlayerStats/PlayerStats';

const ContentContainer = ({ setPlayerProfile, sortPlayerList, players, currPlayer }) => (
  <div className="content-container">
    <SelectBar
      setPlayerProfile={setPlayerProfile}
      sortPlayerList={sortPlayerList}
      players={players}
      currPlayer={currPlayer}
    />
    <PlayerBio player={currPlayer} />
    <div className="content-container__bottom">
      <PlayerStats player={currPlayer} />
      <PlayerImages urls={currPlayer.urls} />
    </div>

  </div>
)

export default ContentContainer;