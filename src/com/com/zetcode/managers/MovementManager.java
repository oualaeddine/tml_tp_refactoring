package com.zetcode.managers;

import com.zetcode.Board;
import com.zetcode.models.actors.moving.Baggage;
import com.zetcode.models.actors.moving.Player;
import com.zetcode.models.actors.world.Wall;

import java.util.ArrayList;

import static com.zetcode.managers.CollisionManager.*;
import static com.zetcode.Board.SPACE;

@SuppressWarnings({"SingleStatementInBlock", "ReturnPrivateMutableField"})
public class MovementManager {

    private Player soko;
    private Board board;
    private ArrayList<Wall> walls;
    private ArrayList<Baggage> baggs;

    MovementManager(Board board, ArrayList<Wall> walls, ArrayList<Baggage> baggs, Player soko) {

        this.board = board;
        this.walls = walls;
        this.baggs = baggs;
        this.soko = soko;
    }


    Player moveDown() {
        if (CollisionManager.checkWallCollision(BOTTOM_COLLISION, soko, walls)) {
            return soko;
        }
        if (CollisionManager.checkBagCollision(BOTTOM_COLLISION, soko, baggs, walls, board)) {
            return soko;
        }
        soko.move(0, SPACE);
        return soko;
    }

    Player moveUp() {
        if (CollisionManager.checkWallCollision(TOP_COLLISION, soko, walls)) {
            return soko;

        }

        if (CollisionManager.checkBagCollision(TOP_COLLISION, soko, baggs, walls, board)) {
            return soko;

        }
        soko.move(0, -SPACE);
        return soko;

    }

    Player moveRight() {
        if (CollisionManager.checkWallCollision(RIGHT_COLLISION, soko, walls)) {
            return soko;

        }

        if (CollisionManager.checkBagCollision(RIGHT_COLLISION, soko, baggs, walls, board)) {
            return soko;

        }

        soko.move(SPACE, 0);
        return soko;

    }

    Player moevLeft() {
        if (CollisionManager.checkWallCollision(LEFT_COLLISION, soko, walls)) {
            return soko;
        }
        if (CollisionManager.checkBagCollision(LEFT_COLLISION, soko, baggs, walls, board)) {
            return soko;
        }
        soko.move(-SPACE, 0);
        return soko;
    }
}
