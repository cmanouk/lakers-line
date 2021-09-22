package com.chasemanoukian.yourfavoritelakersbackend.runnables;

import com.chasemanoukian.yourfavoritelakersbackend.services.PlayerService;

public class QueueRunnable {
    private final String _id;
    private final PlayerService playerService;

    public QueueRunnable(String _id, PlayerService playerService) {
        this._id = _id;
        this.playerService = playerService;
    }

    public void executeRunnable(String link) {
        WebParserRunnable runnable = new WebParserRunnable(_id, link.concat(_id), playerService);
        Thread thread = new Thread(runnable);
        thread.start();
    }
}
