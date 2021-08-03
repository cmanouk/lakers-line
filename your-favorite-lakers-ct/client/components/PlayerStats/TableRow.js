import React from 'react';

const TableRow = ({ rowKey, keys, stats }) => (
  <tr>
    <td>{rowKey}</td>
    {keys.map((k) => <td>{stats[k]}</td>)}
  </tr>
)

export default TableRow;