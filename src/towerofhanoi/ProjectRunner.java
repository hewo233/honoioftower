package towerofhanoi;

import java.util.Scanner;

public class ProjectRunner {
    public static void main(String[] args) {
        int disks = 5;
        if(args.length == 1) {
            disks = Integer.parseInt(args[0]);
        }
        PuzzleWindow currentWindow = new PuzzleWindow(new HanoiSolver(disks));
    }
}
