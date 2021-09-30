import React, { useState, useEffect } from 'react';
import axios from 'axios';
import LoaderContainer from './LoaderContainer/LoaderContainer';
import ContentContainer from './ContentContainer/ContentContainer';

const App = () => {
  const [ players, setPlayers ] = useState([]);
  const [ currPlayer, setCurrPlayer ] = useState({});
  const [ loading, setLoading ] = useState(true);
  const [ error, setError ] = useState('');

  useEffect(() => {
    axios.get('/api/players')
      .then((res) => {
        const sorted = res.data.sort((a, b) => a['firstName'] > b['firstName'] ? 1 : -1);
        setPlayers(sorted);
        setCurrPlayer(sorted[0]);
      })
      .catch((err) => {
        setError(err);
      })
      .finally(() => setLoading(false), 2000);
  }, [])

  function setPlayerProfile(id) {
    const player = players.filter((p) =>
      p._id === id)[0];
    setCurrPlayer(player);
  }

  function sortPlayerList(input) {
    let sorted;
    if (input.length > 3) {
      sorted = [...players].sort((a, b) =>
        a[input] > b[input]? 1 : -1);
    } else {
      sorted = [...players].sort((a, b) =>
        b.seasonStats[input] - a.seasonStats[input]);
    }
    setPlayers(sorted);
    setCurrPlayer(sorted[0]);
  }

  return (
    <div>
      {loading ?
      <LoaderContainer />:

      error ?
      <Error error={error} /> :

      <ContentContainer
        setPlayerProfile={setPlayerProfile}
        sortPlayerList={sortPlayerList}
        players={players}
        currPlayer={currPlayer}
      />}
    </div>
  )
}

export default App;