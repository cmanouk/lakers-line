package com.chasemanoukian.yourfavoritelakersbackend;

public class QueueRunnable {
    private static final String playerLink = "https://www.espn.com/nba/player/_/id/";
    private String _id;
    private final PlayerService playerService;

    public QueueRunnable(String _id, PlayerService playerService) {
        this._id = _id;
        this.playerService = playerService;
    }

    public void executeRunnable() {
        DataQueriesRunnable runnable = new DataQueriesRunnable();
        runnable.set_id(_id);
        runnable.setLink(playerLink.concat(_id));
        runnable.setPlayerService(playerService);

        Thread thread = new Thread(runnable);
        thread.start();
    }
}
