package com.zetcode.managers;

import com.zetcode.Board;
import com.zetcode.models.*;
import com.zetcode.models.actors.world.Area;
import com.zetcode.models.actors.moving.Baggage;
import com.zetcode.models.actors.moving.Player;
import com.zetcode.models.actors.world.Wall;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import static com.zetcode.Board.SPACE;
import static com.zetcode.Sokoban.OFFSET;

public class BoardManager {
    private boolean isCompleted = false;

    private MovementManager movementManager;
    private ArrayList<Wall> walls;
    private ArrayList<Baggage> baggs;
    private ArrayList<Area> areas;
    private Player soko;

    private int mX = OFFSET;
    private int mY = OFFSET;
    private int w = 0;
    private int h = 0;
    private String level;
    private Board board;

    public BoardManager(Board board) {

        this.board = board;
    }

    public int getBoardWidth() {
        return this.w;
    }

    public int getBoardHeight() {
        return this.h;
    }

    public void initWorld(String level) {
        this.level = level;
        initContent();
        for (int i = 0; i < level.length(); i++) {

            char item = level.charAt(i);

            putItem(item);
        }
        movementManager = new MovementManager(board, walls, baggs, soko);

    }

    private void initContent() {
        walls = new ArrayList<>();
        baggs = new ArrayList<>();
        areas = new ArrayList<>();
    }

    private void putItem(char item) {
        switch (item) {
            case '\n':
                putSpace();
                break;
            case '#':
                putWall();
                break;
            case '$':
                puBaggage();
                break;
            case '.':
                putArea();
                break;
            case '@':
                putPlayer();
                break;
            case ' ':
                addSpace();
                break;
            default:
                break;
        }
    }

    private void addSpace() {
        mX += SPACE;
    }

    private void putPlayer() {
        soko = new Player(mX, mY);
        mX += SPACE;
    }

    private void putArea() {
        Area a;
        a = new Area(mX, mY);
        areas.add(a);
        mX += SPACE;
    }

    private void puBaggage() {
        Baggage b;
        b = new Baggage(mX, mY);
        baggs.add(b);
        mX += SPACE;
    }

    private void putWall() {
        Wall wall;
        wall = new Wall(mX, mY);
        walls.add(wall);
        mX += SPACE;
    }

    private void putSpace() {
        mY += SPACE;

        if (this.w < mX) {
            this.w = mX;
        }

        mX = OFFSET;
        h = mY;

    }

    public void handleKey(int key) {
        switch (key) {
            case KeyEvent.VK_LEFT:
                soko = movementManager.moevLeft();
                break;
            case KeyEvent.VK_RIGHT:
                soko = movementManager.moveRight();
                break;
            case KeyEvent.VK_UP:
                soko = movementManager.moveUp();
                break;
            case KeyEvent.VK_DOWN:
                soko = movementManager.moveDown();
                break;
            case KeyEvent.VK_R:
                restartLevel();
                break;
            default:
                break;
        }
    }

    public ArrayList<Actor> getWorld() {
        ArrayList<Actor> world = new ArrayList<>();
        world.addAll(walls);
        world.addAll(areas);
        world.addAll(baggs);
        world.add(soko);
        return world;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    private void restartLevel() {

        areas.clear();
        baggs.clear();
        walls.clear();

        initWorld(level);

        if (isCompleted) {
            isCompleted = false;
        }
    }

    public void completed() {

        int nOfBags = baggs.size();
        int finishedBags = 0;

        for (Baggage bag : baggs) {

            for (int j = 0; j < nOfBags; j++) {

                Area area = areas.get(j);

                if (bag.x() == area.x() && bag.y() == area.y()) {

                    finishedBags += 1;
                }
            }
        }

        if (finishedBags == nOfBags) {

            isCompleted = true;
            board.repaint();
        }
    }
}
