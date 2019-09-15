package com.zetcode.managers;

import com.zetcode.models.Actor;
import com.zetcode.models.actors.moving.Baggage;
import com.zetcode.Board;
import com.zetcode.models.actors.world.Wall;

import java.util.ArrayList;

import static com.zetcode.Board.SPACE;

class CollisionManager {
    static final int
            LEFT_COLLISION = 1,
            RIGHT_COLLISION = 2,
            TOP_COLLISION = 3,
            BOTTOM_COLLISION = 4;


    private static boolean isLeftCollision(Actor actor1, Actor actor2) {
        return actor2.x() - SPACE == actor1.x() && actor2.y() == actor1.y();
    }

    private static boolean isRightCollision(Actor actor1, Actor actor2) {
        return actor2.x() + SPACE == actor1.x() && actor2.y() == actor1.y();
    }

    private static boolean isTopCollision(Actor actor1, Actor actor2) {
        return actor2.y() - SPACE == actor1.y() && actor2.x() == actor1.x();
    }

    private static boolean isBottomCollision(Actor actor1, Actor actor2) {
        return actor2.y() + SPACE == actor1.y() && actor2.x() == actor1.x();
    }


    static boolean checkWallCollision(int type, Actor actor, ArrayList<Wall> walls) {
        switch (type) {
            case LEFT_COLLISION:
                return checkLeftCollision(actor, walls);
            case RIGHT_COLLISION:
                return checkRightCollision(actor, walls);
            case TOP_COLLISION:
                return checkTopCollision(actor, walls);
            case BOTTOM_COLLISION:
                return checkBottomCollision(actor, walls);
            default:
                return false;
        }
    }

    private static boolean checkBottomCollision(Actor actor, ArrayList<Wall> walls) {
        for (Wall wall : walls) {
            if (isBottomCollision( wall,actor)) {
                return true;
            }
        }
        return false;
    }

    private static boolean checkTopCollision(Actor actor, ArrayList<Wall> walls) {
        for (Wall wall : walls) {
            if (isTopCollision( wall,actor)) {
                return true;
            }
        }
        return false;
    }

    private static boolean checkRightCollision(Actor actor, ArrayList<Wall> walls) {
        for (Wall wall : walls) {
            if (isRightCollision( wall,actor)) {
                return true;
            }
        }
        return false;
    }

    private static boolean checkLeftCollision(Actor actor, ArrayList<Wall> walls) {
        for (Wall wall : walls) {
            if (isLeftCollision( wall,actor)) {
                return true;
            }
        }
        return false;
    }

    static boolean checkBagCollision(int type, Actor soko, ArrayList<Baggage> baggs, ArrayList<Wall> walls, Board board) {
        switch (type) {
            case LEFT_COLLISION:
                return cehckLeftBagCollision(soko, baggs, walls, board);
            case RIGHT_COLLISION:
                return checkRightBagCollision(soko, baggs, walls, board);
            case TOP_COLLISION:
                return checkTopBagCollision(soko, baggs, walls, board);
            case BOTTOM_COLLISION:
                return checkBottomBagCollision(soko, baggs, walls, board);
            default:
                return false;
        }
    }

    private static boolean checkBottomBagCollision(Actor soko, ArrayList<Baggage> baggs, ArrayList<Wall> walls, Board board) {
        for (int i = 0; i < baggs.size(); i++) {
            Baggage bag = baggs.get(i);
            if (isBottomCollision(bag, soko)) {
                for (Baggage item : baggs) {
                    if (!bag.equals(item)) {
                        if (isBottomCollision(item, bag)) {
                            return true;
                        }
                    }
                    if (checkWallCollision(BOTTOM_COLLISION, bag, walls)) {

                        return true;
                    }
                }

                bag.move(0, SPACE);
                board.completed();
            }
        }
        return false;
    }

    private static boolean checkTopBagCollision(Actor soko, ArrayList<Baggage> baggs, ArrayList<Wall> walls, Board board) {
        for (int i = 0; i < baggs.size(); i++) {
            Baggage bag = baggs.get(i);
            if (isTopCollision(bag, soko)) {
                for (Baggage item : baggs) {
                    if (!bag.equals(item)) {
                        if (isTopCollision(item, bag)) {
                            return true;
                        }
                    }
                    if (checkWallCollision(TOP_COLLISION, bag, walls)) {
                        return true;
                    }
                }
                bag.move(0, -SPACE);
                board.completed();
            }
        }
        return false;
    }

    private static boolean checkRightBagCollision(Actor soko, ArrayList<Baggage> baggs, ArrayList<Wall> walls, Board board) {
        for (int i = 0; i < baggs.size(); i++) {
            Baggage bag = baggs.get(i);
            if (isRightCollision(bag, soko)) {
                for (Baggage item : baggs) {
                    if (!bag.equals(item)) {

                        if (isRightCollision(item, bag)) {
                            return true;
                        }
                    }
                    if (checkWallCollision(RIGHT_COLLISION, bag, walls)) {
                        return true;
                    }
                }
                bag.move(SPACE, 0);
                board.completed();
            }
        }
        return false;
    }

    private static boolean cehckLeftBagCollision(Actor soko, ArrayList<Baggage> baggs, ArrayList<Wall> walls, Board board) {
        for (int i = 0; i < baggs.size(); i++) {
            Baggage bag = baggs.get(i);
            if (isLeftCollision(bag, soko)) {
                for (Baggage item : baggs) {
                    if (!bag.equals(item)) {

                        if (isLeftCollision(item, bag)) {
                            return true;
                        }
                    }
                    if (checkWallCollision(LEFT_COLLISION, bag, walls)) {
                        return true;
                    }
                }
                bag.move(-SPACE, 0);
                board.completed();
            }
        }
        return false;
    }
}
