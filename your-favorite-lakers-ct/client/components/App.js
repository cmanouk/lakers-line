import React, { useState, useEffect } from 'react';
import axios from 'axios';
import Header from './Header/Header';
import Select from './Header/Select';
import PlayerBio from './PlayerBio/PlayerBio';
import PlayerImages from './PlayerImages/PlayerImages';
import PlayerStats from './PlayerStats/PlayerStats';
import dummyData from '../dummyData';

const App = () => {
  const [players, setPlayers] = useState([]);
  const [currPlayer, setCurrPlayer] = useState({});

  useEffect(() => {
    // axios.get('/api/players')
    //   .then((res) => {
    //     setPlayers(res.data);
    //     console.log(res.data);
    //   })
    //   .catch((err) => console.log(err));
    setPlayers(dummyData);
    setCurrPlayer(dummyData[0]);
  }, [])

  function setPlayerProfile(id) {
    const player = players.filter((p) => p._id === id)[0];
    setCurrPlayer(player)
  }

  return (
    <div>
      <Header />
      {currPlayer.firstName ?
      <div className="content-container">
        <Select
          setPlayerProfile={setPlayerProfile}
          players={players}
          currPlayer={currPlayer}
        />
        <PlayerBio player={currPlayer} />
        <PlayerImages urls={currPlayer.urls} />
        <PlayerStats player={currPlayer} />
      </div> :
      null
      }
    </div>
  )
}

export default App;