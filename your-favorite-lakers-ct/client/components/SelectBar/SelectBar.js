import React from 'react';
import PlayerSelect from './PlayerSelect';
import SortBySelect from './SortBySelect';

const SelectBar = ({ setPlayerProfile, sortPlayerList, players, currPlayer}) => (
  <form className="select-bar">
    <PlayerSelect
      setPlayerProfile={setPlayerProfile}
      players={players}
      currPlayer={currPlayer}
    />
    <label htmlFor='sort-select'><span>Sort By</span>
      <SortBySelect sortPlayerList={sortPlayerList} />
    </label>
  </form>
)

export default SelectBar;