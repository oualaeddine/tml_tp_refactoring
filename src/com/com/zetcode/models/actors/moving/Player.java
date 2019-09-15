package com.zetcode.models.actors.moving;

import com.zetcode.models.actors.moving.MovingActor;

public class Player extends MovingActor {

    public Player(int x, int y) {
        super(x, y);
        initImage("src/resources/sokoban.png");
    }
}
