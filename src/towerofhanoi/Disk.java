package towerofhanoi;

import cs2.Shape;
import java.awt.Color;
import student.TestableRandom;

public class Disk extends Shape implements Comparable<Disk> {

    Disk(int width) {
        super(0,0,width,PuzzleWindow.DISK_HEIGHT,getRandomColor());
    }

    @Override
    public int compareTo(Disk dk) {
        if(dk == null) throw new IllegalArgumentException();
        return this.getWidth() - dk.getWidth();
    }

    private static Color getRandomColor() {
        TestableRandom myRandom = new TestableRandom();
        int R = Math.abs(myRandom.nextInt()) % 256;
        int G = Math.abs(myRandom.nextInt()) % 256;
        int B = Math.abs(myRandom.nextInt()) % 256;
        return new Color(R,G,B);
    }

    @Override
    public String toString() {
        return Integer.toString(this.getWidth());
    }

    public boolean equals(Object dk) {
        if(this.getClass() != dk.getClass()) return false; // 报我错是吧
        return this.getWidth() == ((Disk) dk).getWidth();
    }

}