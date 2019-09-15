package com.zetcode.models.actors.world;

import com.zetcode.models.Actor;

public class Wall extends Actor {
     public Wall(int x, int y) {
        super(x, y);
        initImage("src/resources/wall.png");
    }
}
