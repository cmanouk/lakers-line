import React, { useState, useEffect } from 'react';
import axios from 'axios';
import Header from './Header/Header';
import ContentContainer from './ContentContainer/ContentContainer';
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

  function sortPlayerList(input) {
    let sorted;
    if (input.length > 3) {
      sorted = [...players].sort((a, b) => a[input] > b[input]? 1 : -1);
    } else {
      sorted = [...players].sort((a, b) => b.seasonStats[input] - a.seasonStats[input]);
    }
    setPlayers(sorted);
    setCurrPlayer(sorted[0]);
  }

  return (
    <div>
      <Header />
      {currPlayer.firstName ?

      <ContentContainer
        setPlayerProfile={setPlayerProfile}
        sortPlayerList={sortPlayerList}
        players={players}
        currPlayer={currPlayer}
      /> :

      null}
    </div>
  )
}

export default App;