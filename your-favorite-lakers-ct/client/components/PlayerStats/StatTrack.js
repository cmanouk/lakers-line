import React from 'react';
import TableRow from './TableRow';

const StatTrack = ({ clsName, header, stats, keys }) => {
  const statRowInfo = clsName === 'previous-ten' ? Object.keys(stats).reverse() : Object.keys(stats);

  return (
    <div className={`${clsName} stat-track`}>
      <h6 className={`${clsName}__header stat-track__header`}>{header}</h6>
      <table className={`${clsName}__stats stat-track__stats`}>
        <thead className='table-head'>
          <tr>{keys.map((k) => <td key={k}>{k}</td>)}</tr>
        </thead>
        <tbody>
          {statRowInfo.map((rowKey, i) =>
          <TableRow
            key={rowKey}
            stats={stats[rowKey]}
            rowKey={rowKey}
            keys={keys.slice(1)}
            index={i}
          />)}
        </tbody>
      </table>
    </div>
  )
}

export default StatTrack;