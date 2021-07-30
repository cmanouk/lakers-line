import React from 'react';

const PlayerBio = ({ player }) => {
  const { firstName, lastName, jerseyNumber, age, position } = player;
  return (
    <section className='player-bio'>
      <div className="player-bio__left">
        <div className="player-bio__img--container">
          <img
            src={`/images/${firstName}${lastName}/1`}
            alt={`${firstName} ${lastName} profile picture`}
            className="player-bio__img"
          />
        </div>
      </div>
      <div className="player-bio__right">
        <div className="player-bio__right--container">
          <h2>{firstName} <span>{lastName}</span></h2>
          <div className="player-bio__right--info">
            <p>{jerseyNumber}</p>
            <p>{position}</p>
            <p>{age}</p>
          </div>
        </div>
      </div>
    </section>
  )
}

export default PlayerBio;