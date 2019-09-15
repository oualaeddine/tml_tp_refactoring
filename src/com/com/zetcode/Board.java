package com.zetcode;

import com.zetcode.managers.BoardManager;
import com.zetcode.models.*;
import com.zetcode.models.actors.moving.Baggage;
import com.zetcode.models.actors.moving.Player;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JPanel;

public class Board extends JPanel {
    public static final int SPACE = 20;
    private BoardManager boardManager;

    private static final String level
            = "    ######\n"
            + "    ##   #\n"
            + "    ##$  #\n"
            + "  ####  $##\n"
            + "  ##  $ $ #\n"
            + "#### # ## #   ######\n"
            + "##   # ## #####  ..#\n"
            + "## $  $          ..#\n"
            + "###### ### #@##  ..#\n"
            + "    ##     #########\n"
            + "    ########\n";

    Board() {
        initBoard();
    }

    private void initBoard() {

        addKeyListener(new TAdapter());
        setFocusable(true);
        boardManager = new BoardManager(this);
        boardManager.initWorld(level);
    }


    @SuppressWarnings("MethodComplexity")


    private void buildWorld(Graphics g) {

        g.setColor(new Color(250, 240, 170));
        g.fillRect(0, 0, this.getWidth(), this.getHeight());

        ArrayList<Actor> world = boardManager.getWorld();


        for (Actor item : world) {
            if (item instanceof Player || item instanceof Baggage) {
                g.drawImage(item.getImage(), item.x() + 2, item.y() + 2, this);
            } else {
                g.drawImage(item.getImage(), item.x(), item.y(), this);
            }
            if (boardManager.isCompleted()) {
                g.setColor(new Color(0, 0, 0));
                g.drawString("Completed", 25, 20);
            }

        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        buildWorld(g);
    }

    public void completed() {
        boardManager.completed();
    }

    int getBoardWidth() {
        return boardManager.getBoardWidth();
    }

    int getBoardHeight() {
        return boardManager.getBoardHeight();
    }

    private class TAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            if (boardManager.isCompleted()) {
                return;
            }
            int key = e.getKeyCode();
            boardManager.handleKey(key);
            repaint();
        }
    }
}
