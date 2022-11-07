/*
Inputs:
    (int) maze = [[0, 1, 1, 0],
    		  [0, 0, 0, 1],
    		  [1, 1, 0, 0],
    		  [1, 1, 1, 0]]
Output:
    (int) 7
Inputs:
    (int) maze = [[0, 0, 0, 0, 0, 0],
    		  [1, 1, 1, 1, 1, 0],
    		  [0, 0, 0, 0, 0, 0],
  		  [0, 1, 1, 1, 1, 1],
    		  [0, 1, 1, 1, 1, 1],
    		  [0, 0, 0, 0, 0, 0]]
Output:
    (int) 11
*/


import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

class Point {
    public int x;
    public int y;

    Point(int y, int x) {
        this.x = x;
        this.y = y;
    }
}

public class Solution {

    public static void calculatePointDistance(int[][] distances, int[][] visited, Queue<Point> q, Point p, int h, int w) {

//        visited[p.y][p.x] = 1;

        if (p.x > 0 && distances[p.y][p.x - 1] > 0)
            distances[p.y][p.x] = Math.max(distances[p.y][p.x], distances[p.y][p.x - 1] + 1);
        if (p.x < w - 1 && distances[p.y][p.x + 1] > 0)
            distances[p.y][p.x] = Math.max(distances[p.y][p.x], distances[p.y][p.x + 1] + 1);
        if (p.y > 0 && distances[p.y - 1][p.x] > 0)
            distances[p.y][p.x] = Math.max(distances[p.y][p.x], distances[p.y - 1][p.x] + 1);
        if (p.y < h - 1 && distances[p.y + 1][p.x] > 0)
            distances[p.y][p.x] = Math.max(distances[p.y][p.x], distances[p.y + 1][p.x] + 1);

        if (p.x > 0 && visited[p.y][p.x - 1] == 0) q.add(new Point(p.y, p.x - 1));
        if (p.x < w - 1 && visited[p.y][p.x + 1] == 0) q.add(new Point(p.y, p.x + 1));
        if (p.y > 0 && visited[p.y - 1][p.x] == 0) q.add(new Point(p.y - 1, p.x));
        if (p.y < h - 1 && visited[p.y + 1][p.x] == 0) q.add(new Point(p.y + 1, p.x));


    }


    public static int solution(int[][] maze) {

        int h = maze.length;
        int w = maze[0].length;
        Queue<Point> nextMoves = new LinkedList<Point>();

        int minimumDistance = Integer.MAX_VALUE;
        int solved;
        LinkedList<Point> walls = new LinkedList<Point>();

        int[][] distancesStart = new int[h][w];
        int[][] visitedStart = new int[h][w];

        int[][] distancesExit = new int[h][w];
        int[][] visitedExit = new int[h][w];

        //       First pass: from Start to Exit
        distancesStart[0][0] = 1;
        visitedStart[0][0] = 1;



        nextMoves.add(new Point(1, 0));
        nextMoves.add(new Point(0, 1));

        while (!nextMoves.isEmpty()) {

            Point p = nextMoves.poll();
            if (visitedStart[p.y][p.x] == 0)
                if (maze[p.y][p.x] == 1) walls.add(new Point(p.y, p.x));

            visitedStart[p.y][p.x] = 1;
            if (maze[p.y][p.x] != 1) calculatePointDistance(distancesStart, visitedStart, nextMoves, p, h, w);

        }

        solved = distancesStart[h - 1][w - 1]; //soulution without removing wall



//       Second pass: from Exit to Start
        distancesExit[h - 1][w - 1] = 1;
        visitedExit[h - 1][w - 1] = 1;



        nextMoves.add(new Point(h - 2, w - 1));
        nextMoves.add(new Point(h - 1, w - 2));

        while (!nextMoves.isEmpty()) {
            Point p2 = nextMoves.poll();
            visitedExit[p2.y][p2.x] = 1;
            if (maze[p2.y][p2.x] != 1)
                calculatePointDistance(distancesExit, visitedExit, nextMoves, p2, h, w);
        }


        if (solved > 0) minimumDistance = Math.min(minimumDistance, solved);


        for (int o = 0; o < walls.size(); o++) {
            Point wall = walls.get(o);
            int y = wall.y;
            int x = wall.x;

            ArrayList<Integer> neighbours = new ArrayList<Integer>();
            ArrayList<Integer> neighbours2 = new ArrayList<Integer>();

            if (y - 1 >= 0 && distancesStart[y - 1][x] > 0) neighbours.add(distancesStart[y - 1][x]);
            if (y + 1 < h && distancesStart[y + 1][x] > 0) neighbours.add(distancesStart[y + 1][x]);
            if (x - 1 >= 0 && distancesStart[y][x - 1] > 0) neighbours.add(distancesStart[y][x - 1]);
            if (x + 1 < w && distancesStart[y][x + 1] > 0) neighbours.add(distancesStart[y][x + 1]);

            if (y - 1 >= 0 && distancesExit[y - 1][x] > 0) neighbours2.add(distancesExit[y - 1][x]);
            if (y + 1 < h && distancesExit[y + 1][x] > 0) neighbours2.add(distancesExit[y + 1][x]);
            if (x - 1 >= 0 && distancesExit[y][x - 1] > 0) neighbours2.add(distancesExit[y][x - 1]);
            if (x + 1 < w && distancesExit[y][x + 1] > 0) neighbours2.add(distancesExit[y][x + 1]);

            Collections.sort(neighbours);
            Collections.sort(neighbours2);

            // minimum distance throu wall
            if (neighbours.size() > 0 && neighbours2.size() > 0) {
                minimumDistance = Math.min(minimumDistance, 1 + neighbours.get(0) + neighbours2.get(0));
            }

        }

        return minimumDistance;
    }


    public static void main(String[] args) {



        int[][] maze = {{0, 0, 0, 0, 0},
                {1, 1, 1, 1, 0},
                {0, 0, 0, 0, 0},
                {0, 1, 0, 1, 1},
                {0, 0, 0, 0, 0}};  //9

        int[][] maze2 = {   {0, 1, 1, 0},
                {0, 0, 0, 1},
                {1, 1, 0, 0},
                {1, 1, 1, 0}}; //7


        int[][] maze3 = {{0, 0, 0, 0, 0, 0},
                {1, 1, 1, 1, 1, 0},
                {0, 0, 0, 0, 0, 0},
                {1, 1, 0, 1, 1, 1},
                {0, 1, 0, 1, 1, 1},
                {0, 0, 0, 0, 0, 0}}; //11

        int[][] maze4 = {{0, 0, 0, 0, 0, 0},
                {1, 1, 1, 1, 1, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 1, 1, 1, 1, 1},
                {0, 1, 1, 1, 1, 1},
                {0, 0, 0, 0, 0, 0}}; //11

        System.out.println("Maze 1 distance: " + solution(maze));
        System.out.println("Maze 2 distance: " + solution(maze2));
        System.out.println("Maze 3 distance: " + solution(maze3));
        System.out.println("Maze 4 distance: " + solution(maze4));
    }

}