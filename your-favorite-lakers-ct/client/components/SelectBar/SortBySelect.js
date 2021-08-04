import React from 'react';

const SortBySelect = ({ sortPlayerList }) => {
  const options = {
    'firstName': 'Alphabetical (first)',
    'lastName': 'Alphabetical (last)',
    'PTS': 'Points',
    'REB': 'Rebounds',
    'AST': 'Assists',
    'STL': 'Steals',
    'BLK': 'Blocks'
  };

  return (
    <select
      onChange={(e) => sortPlayerList(e.target.value)}
      className='sort-by-select'
      id='sort-select'
    >
      {Object.keys(options).map((o) => (
        <option key={o} value={o}>
          {options[o]}
        </option>
      ))}
    </select>
  )
}

export default SortBySelect;