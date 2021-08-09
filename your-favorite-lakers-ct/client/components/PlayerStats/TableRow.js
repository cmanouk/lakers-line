import React from 'react';

const TableRow = ({ rowKey, keys, stats, index }) => (
  <tr className={!(index % 2) ? 'dark-purple' : null}>
    <td>{rowKey}</td>
    {keys.map((k) => <td key={k}>{stats[k]}</td>)}
  </tr>
)

export default TableRow;