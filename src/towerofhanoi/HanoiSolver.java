package towerofhanoi;

import java.util.Observable;
public class HanoiSolver extends Observable{
    private final Tower left, middle, right;

    private final int numDisks;

    HanoiSolver(int numDisks) {
        this.left = new Tower(Position.LEFT);
        this.middle = new Tower(Position.MIDDLE);
        this.right = new Tower(Position.RIGHT);
        this.numDisks = numDisks;
    }

    public int disks() {
        return numDisks;
    }

    public Tower getTower(Position pos) {
        switch (pos) {

            case LEFT -> {
                return this.left;
            }

            case MIDDLE, DEFAULT -> {
                return this.middle;
            }

            case RIGHT -> {
                return this.right;
            }

        }
        return null;
    }

    @Override
    public String toString() {
        return this.left.toString()+this.middle.toString()+this.right.toString();
    }

    private void move(Tower source, Tower destination) {
        Disk nowDisk = source.pop();
        destination.push(nowDisk);
        setChanged();
        notifyObservers(destination.position());
    }

//以下请你自己对照文档实现 solveTowers 和 solve

}
