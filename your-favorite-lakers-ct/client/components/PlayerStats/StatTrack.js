import React from 'react';
import TableRow from './TableRow';

const StatTrack = ({ clsName, header, stats, keys }) => {
  const statRowInfo = Object.keys(stats);

  return (
    <div className={`${clsName}`}>
      <h6 className={`${clsName}__header`}>{header}</h6>
      <table className={`${clsName}__stats`}>
        <thead>
          <tr>{keys.map((k) => <td>{k}</td>)}</tr>
        </thead>
        <tbody>
          {statRowInfo.map((rowKey) =>
            <TableRow key={rowKey} stats={stats[rowKey]} rowKey={rowKey} keys={keys.slice(1)} />)}
        </tbody>
      </table>
    </div>
  )
}

export default StatTrack;