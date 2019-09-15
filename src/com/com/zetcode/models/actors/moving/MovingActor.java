package com.zetcode.models.actors.moving;

import com.zetcode.models.Actor;

public class MovingActor extends Actor {
    MovingActor(int x, int y) {
        super(x, y);
    }
    public void move(int x, int y) {
        int dx = x() + x;
        int dy = y() + y;
        setX(dx);
        setY(dy);
    }
}
