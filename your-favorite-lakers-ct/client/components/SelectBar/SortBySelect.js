import React from 'react';

const SortBySelect = ({ sortPlayerList }) => {
  const options = [
    'alph-f',
    'alph-l',
    'pts',
    'reb',
    'ast',
    'stl',
    'blk'
  ];

  return (
    <select
      onChange={() => sortPlayerList(e.target.value)}
    >
      <option></option>
    </select>
  )
}

export default SortBySelect;