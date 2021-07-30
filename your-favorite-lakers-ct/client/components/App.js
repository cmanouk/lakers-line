import React, { useState, useEffect } from 'react';
import axios from 'axios';
import Header from './Header/Header';
import PlayerBio from './PlayerBio/PlayerBio';
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
    console.log(dummyData);
  }, [])

  // useEffect(() => {
  //   console.log(currPlayer)
  // }, [currPlayer])

  function setPlayerProfile(id) {
    const player = players.filter((p) => p._id === id)[0];
    setCurrPlayer(player)
  }

  return (
    <div>
      <Header
        setPlayerProfile={setPlayerProfile}
        players={players}
        player={currPlayer}
      />
      <PlayerBio player={currPlayer} />
      {/*
      PlayerBio
      PlayerImages
      PlayerStats
      */}
    </div>
  )
}

export default App;