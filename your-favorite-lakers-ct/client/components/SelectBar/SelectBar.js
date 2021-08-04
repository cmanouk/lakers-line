import React from 'react';
import PlayerSelect from './PlayerSelect';
import SortBySelect from './SortBySelect';

const SelectBar = ({ setPlayerProfile, sortPlayerList, players, currPlayer}) => (
  <div className="select-bar">
    <PlayerSelect
      setPlayerProfile={setPlayerProfile}
      players={players}
      currPlayer={currPlayer}
    />
    <SortBySelect sortPlayerList={sortPlayerList} />
  </div>
)

export default SelectBar;