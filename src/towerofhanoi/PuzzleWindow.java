package towerofhanoi;

import cs2.Button;
import cs2.Shape;
import cs2.Window;
import cs2.WindowSide;

import java.awt.*;
import java.util.Currency;
import java.util.Observable;
import java.util.Observer;

public class PuzzleWindow implements Observer {

    private HanoiSolver game;
    private Shape left;
    private Shape middle;
    private Shape right;
    private Window window;
    /**
     * A factor in which the width of the disks are multiplied by
     */
    public static final int WIDTH_FACTOR = 15;
    /**
     * The vertical gap between each disk on the tower
     */
    public static final int DISK_GAP = 0;
    /**
     * The height of each disk on the tower
     */
    public static final int DISK_HEIGHT = 15;


    /**
     * Creates a new PuzzleWindow view for a given HanoiSolver game
     *
     * @param g the game to create a view for
     */
    public PuzzleWindow(HanoiSolver g) {
        //TODO: implement the instructions given
        this.game = g;
        game.addObserver(this);

        window = new Window("Tower of Hanoi");
        window.setSize(1200,800);

        //The height and Y location of each pole are the same
        int poleHeight = 400;
        int poleY = (window.getGraphPanelHeight() / 2) - (poleHeight / 2);
        left = new Shape((200 - 15 /*width*/ / 2),
                poleY, 15, poleHeight, new Color(50, 50, 50));
        middle = new Shape(((window.getGraphPanelWidth() / 2) - 15 / 2),
                poleY, 15, poleHeight, new Color(50, 50, 50));
        right = new Shape(((window.getGraphPanelWidth() - 200) - 15 / 2),
                poleY, 15, poleHeight, new Color(50, 50, 50));

        for (int width = (game.disks() + 1) * WIDTH_FACTOR; width > WIDTH_FACTOR; width -= WIDTH_FACTOR) {
            Disk currentDisk = new Disk(width);
            game.getTower(Position.LEFT).push(currentDisk);
            currentDisk.moveTo(left.getX() + left.getWidth() / 2 - currentDisk.getWidth() / 2,
                    left.getY() + left.getHeight() - game.getTower(Position.LEFT).size() * DISK_HEIGHT - (game.getTower(Position.LEFT).size() - 1) * DISK_GAP);//姑且加上间隙
            window.addShape(currentDisk);
        }

        window.addShape(left);
        window.addShape(middle);
        window.addShape(right);
        window.moveToBack(left);
        window.moveToBack(middle);
        window.moveToBack(right);

        Button solveButton = new Button("Solve");
        window.addButton(solveButton,WindowSide.SOUTH);
        solveButton.onClick(this,"clickedSolve");
        window.repaint();


    }

    private void moveDisk(Position position) {
        //TODO: Implement this method yourself. See instructions for tips.
        Disk currentDisk = game.getTower(position).peek();

        Shape currentPole = null;
        switch (position) {

            case LEFT -> {
                currentPole = left;
            }

            case MIDDLE, DEFAULT -> {
                currentPole = middle;
            }

            case RIGHT -> {
                currentPole = right;
            }
        }
        currentDisk.moveTo(currentPole.getX() + left.getWidth() / 2 - currentDisk.getWidth() / 2,
                currentPole.getY() + currentPole.getHeight() - game.getTower(position).size() * DISK_HEIGHT - (game.getTower(position).size() - 1) * DISK_GAP);//姑且加上间隙


    }

    /**
     * Updates the view whenever a disk is moved in the back-end
     *
     * @param o   The observable that triggered the update
     * @param arg arguments sent by the game; should be a position
     */
    @Override
    public void update(Observable o, Object arg) {
        if (arg.getClass() == Position.class) {
            Position position = (Position)arg;
            moveDisk(position);
            sleep();
        }
    }

    /**
     * Runs when the Solve button is clicked, tells the puzzle to start solving
     *
     * @param button the button that was clicked
     */
    public void clickedSolve(Button button) {
        button.disable();
        new Thread() {
            public void run() {
                game.solve();
            }
        }.start();
    }

    private void sleep() {
        try {
            Thread.sleep(500);
        }
        catch (Exception e) {
        }
    }
}
