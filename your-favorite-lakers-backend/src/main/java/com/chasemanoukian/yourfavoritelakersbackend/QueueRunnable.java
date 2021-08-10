package com.chasemanoukian.yourfavoritelakersbackend;

public class QueueRunnable {
    private final static String playerLink = "https://www.espn.com/nba/player/_/id/";
    private final String _id;
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

    public void executePrevSeasonsRunnable() {
        PrevSeasonsStatsRunnable runnable = new PrevSeasonsStatsRunnable();
        runnable.set_id(_id);
        runnable.setLink("https://www.espn.com/nba/player/stats/_/id/" + _id);
        runnable.setPlayerService(playerService);

        Thread thread = new Thread(runnable);
        thread.start();
    }

    public void executePrevTenRunnable() {
        PrevTenRunnable runnable = new PrevTenRunnable();
        runnable.set_id(_id);
        runnable.setLink("https://www.espn.com/nba/player/gamelog/_/id/" + _id);
        runnable.setPlayerService(playerService);

        Thread thread = new Thread(runnable);
        thread.start();
    }
}
