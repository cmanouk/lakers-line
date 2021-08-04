import React from 'react';

const TableRow = ({ rowKey, keys, stats }) => (
  <tr>
    <td>{rowKey}</td>
    {keys.map((k) => <td key={k}>{stats[k]}</td>)}
  </tr>
)

export default TableRow;