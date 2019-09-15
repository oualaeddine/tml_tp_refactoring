package com.zetcode.models.actors.moving;

import com.zetcode.models.actors.moving.MovingActor;

public class Baggage extends MovingActor {
     public Baggage(int x, int y) {
        super(x, y);
        initImage("src/resources/baggage.png");
    }
}
