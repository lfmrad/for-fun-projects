import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import javax.xml.namespace.QName;

import java.util.Scanner;

public class GameOfLife {

    // Block reference: ■ □ x

    public static final int gridSizeX = 60;
    public static final int gridSizeY = 60;
    public static char[][] gameGrid = new char[gridSizeX][gridSizeY];
    public static char[][] gameGridMirror = new char[gridSizeX][gridSizeY];
    public static final char buildingBlock = ' ';
    public static final char aliveBlock = '■';
    public static final char deadBlock = 'x';
    public static final char dyingBlock = ' ';
    public static final double aliveGenerationChance = 0.42d;
    public static final double deadGenerationChance = 0.05d;
    public static int genCount = 0;
    public static final boolean hideDeaths = true;
    public static final int fps = 25;

    public enum PatternLib {
        PULSAR, 
        METAPIXEL,
        TEST,
        THINGY,
        GOSPER_GLIDER_GUN,
        TEST2,
    }

    public static void main(String[] args) {
        setTheBoard();
        patternLoader(PatternLib.GOSPER_GLIDER_GUN,10,10);
        // patternLoader(PatternLib.PULSAR,5,5);
        // patternLoader(PatternLib.PULSAR,0,25);
        // patternLoader(PatternLib.METAPIXEL,15,15);
        // patternLoader(PatternLib.TEST,35,15);
        // patternLoader(PatternLib.THINGY,10,35);
        // populate();
        System.out.println("LOADING...");
        //graphicEngine();
    

        // System.out.println("2,6 neighbor = " + countNeighbors(2,6));
        // System.out.println("3,6 neighbor = " + countNeighbors(3,6));
        // System.out.println("4,6 neighbor = " + countNeighbors(4,6));
   
        /* 
        Scanner in = new Scanner(System.in);
        int a = in.nextInt();
        int b = in. nextInt();

        System.out.println("confirm " + a + " / " + b);
        System.out.println(countNeighbors(a,b));
        */
        ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor();
        Runnable gameLoop = () -> { graphicEngine(); nextGen(); };
        ses.scheduleAtFixedRate(gameLoop, 2000,1000/fps, TimeUnit.MILLISECONDS); 
    }

    public static void setTheBoard() {
        for (int x = 0; x < gridSizeX; x++) {
            for (int y = 0; y < gridSizeY; y++) {
                gameGrid[x][y] = deadBlock;
            }
        }
    }
    
    public static void populate() {
        double aliveDeadLotto;

        for (int x = 0; x < gridSizeX; x++) {
            for (int y = 0; y < gridSizeY; y++) {
                aliveDeadLotto = Math.random();
                if (1-aliveGenerationChance <= aliveDeadLotto) {
                    gameGrid[x][y] = aliveBlock;
                //System.out.println(aliveDeadLotto);
                }
            }
        }
    }

    public static void patternLoader(PatternLib selectedPattern, int right, int down) {
        String seedToLoad, seed01, seed02, seed03, seed04, seed05, seed06, seed07, seed08, seed09, seed10;
        seedToLoad = seed01 = seed02 = seed03 = seed04 = seed05 = seed06 = seed07 = seed08 = seed09 = seed10 = "";

        switch (selectedPattern) {
            case PULSAR:
                seed01 = "x x x x ■ x x x x x";
                seed02 = "x x x x ■ x x x x x";
                seed03 = "x x x x ■ x x x x x";
                seed04 = "■ ■ ■ x x x ■ ■ ■ x";
                seed05 = "x x x x ■ x x x x x";
                seed06 = "x x x x ■ x x x x x";
                seed07 = "x x x x ■ x x x x x";
                seed08 = "x x x x x x x x x x";
                seed09 = "x x x x x x x x x x";
                seed10 = "x x x x x x x x x x";
                break;
            case METAPIXEL:
                seed01 = "x x ■ x x ■ x ■ x x";
                seed02 = "■ ■ x ■ x ■ ■ ■ x x";
                seed03 = "x ■ x x x x x x ■ x";
                seed04 = "■ ■ x x x x x ■ x x";
                seed05 = "x x x x x x x x x x";
                seed06 = "x ■ x x x x x ■ ■ x";
                seed07 = "■ x x x x x x ■ x x";
                seed08 = "x ■ ■ ■ x ■ x ■ ■ x";
                seed09 = "x ■ x ■ x x ■ x x x";
                seed10 = "x x x x x x x x x x";
                break;
            case TEST:
                seed01 = "■ x x x x x x x x x";
                seed02 = "x ■ x ■ ■ x x x ■ x";
                seed03 = "x x ■ x x ■ x x ■ x";
                seed04 = "x x x x x ■ x x ■ x";
                seed05 = "x x x x ■ x x x ■ x";
                seed06 = "x x x ■ x x x ■ x x";
                seed07 = "x x x ■ x x ■ x x x";
                seed08 = "x x x x ■ ■ x x x x";
                seed09 = "x x x x x x x x x x";
                seed10 = "x x x x x x x x x x";
                break;
            case THINGY:
                seed01 = "x x x x x x x x x x";
                seed02 = "x x x x ■ x x x x x";
                seed03 = "x x x x ■ x x x x x";
                seed04 = "x x x x ■ x x x x x";
                seed05 = "x x x x ■ x x x x x";
                seed06 = "x x x x ■ x x x x x";
                seed07 = "x x x x ■ x x x x x";
                seed08 = "x x x x ■ x x x x x";
                seed09 = "x x x x x x x x x x";
                seed10 = "x x x x x x x x x x";
                break;
            case GOSPER_GLIDER_GUN:
                seed01 = "x x x x x x x x x x x x x x x x x x x x x x x x ■ x x x x x x x x x x x x x x x"; 
                seed02 = "x x x x x x x x x x x x x x x x x x x x x x ■ x ■ x x x x x x x x x x x x x x x"; 
                seed03 = "x x x x x x x x x x x x ■ ■ x x x x x x ■ ■ x x x x x x x x x x x x ■ ■ x x x x"; 
                seed04 = "x x x x x x x x x x x ■ x x x ■ x x x x ■ ■ x x x x x x x x x x x x ■ ■ x x x x"; 
                seed05 = "■ ■ x x x x x x x x ■ x x x x x ■ x x x ■ ■ x x x x x x x x x x x x x x x x x x"; 
                seed06 = "■ ■ x x x x x x x x ■ x x x ■ x ■ ■ x x x x ■ x ■ x x x x x x x x x x x x x x x"; 
                seed07 = "x x x x x x x x x x ■ x x x x x ■ x x x x x x x ■ x x x x x x x x x x x x x x x"; 
                seed08 = "x x x x x x x x x x x ■ x x x ■ x x x x x x x x x x x x x x x x x x x x x x x x"; 
                seed09 = "x x x x x x x x x x x x ■ ■ x x x x x x x x x x x x x x x x x x x x x x x x x x"; 
                seed10 = "x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x";
                break;
            case TEST2:
                seed01 = "x x x x x x x x x x";
                seed02 = "x x x x ■ x x x x x";
                seed03 = "x x x ■ ■ ■ x x x x";
                seed04 = "x x x x ■ x x x x x";
                seed05 = "x x x x x x x x x x";
                seed06 = "x x x x x x x x x x";
                seed07 = "x x x x x x x x x x";
                seed08 = "x x x x x x x x x x";
                seed09 = "x x x x x x x x x x";
                seed10 = "x x x x x x x x x x";
                break;
            default:
                seed01 = "x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x";
                seed02 = "x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x"; 
                seed03 = "x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x";
                seed04 = "x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x";
                seed05 = "x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x";
                seed06 = "x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x";
                seed07 = "x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x";
                seed08 = "x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x";
                seed09 = "x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x";
                seed10 = "x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x";
        }

        for (int x = 0+down; x < 10+down; x++) {
            switch(x+1-down) {
                case 1:
                    seedToLoad = seed01;
                    break;
                case 2:
                    seedToLoad = seed02;
                    break;
                case 3:
                    seedToLoad = seed03;
                    break;
                case 4:
                    seedToLoad = seed04;
                    break;
                case 5:
                    seedToLoad = seed05;
                    break;
                case 6:
                    seedToLoad = seed06;
                    break;
                case 7:
                    seedToLoad = seed07;
                    break;
                case 8:
                    seedToLoad = seed08;
                    break;
                case 9:
                    seedToLoad = seed09;
                    break;
                case 10:
                    seedToLoad = seed10;
                    break;
            }
            seedToLoad = seedToLoad.replace(" ","");
            // System.out.println("index " + (x+1-down) + " / " + "Seed: " + seedToLoad);
            for (int y = 0+right; y < seedToLoad.length()-1+right; y++) {
                gameGrid[x][y] = seedToLoad.charAt(y-right);
            }
        }
    }

    public static void nextGen() {
        int aliveNeighbors;

        gridStateTransfer(0);

        for (int x = 0; x < gridSizeX; x++) {
            for (int y = 0; y < gridSizeY; y++) {
                //if (gameGrid[x][y] != buildingBlock) {

                    aliveNeighbors = countNeighbors(x,y);
                    // System.out.println("Coordinates: " + x + "/" + y + " - Neighbors: " + aliveNeighbors);

                    if (gameGrid[x][y] == aliveBlock && !(aliveNeighbors == 2) && !(aliveNeighbors == 3)) {
                        gameGridMirror[x][y] = dyingBlock;
                    } else if ((gameGrid[x][y] == deadBlock || gameGrid[x][y] == dyingBlock) && (aliveNeighbors == 3)) {
                        gameGridMirror[x][y] = aliveBlock;
                    } else if (gameGrid[x][y] == dyingBlock) {
                        gameGridMirror[x][y] = deadBlock;
                    }
                //}
            }
        }
        gridStateTransfer(1);
        genCount++;
        System.out.println("Generation number: " + genCount);
        System.out.println("Conway's Game of Life by lfmrad.xyz");
    }

    public static void gridStateTransfer(int mode) {
        if(mode == 0) {
            for (int x = 0; x < gridSizeX; x++) {
                for (int y = 0; y < gridSizeY; y++) {
                    gameGridMirror[x][y] = gameGrid[x][y];
                }
            }
        } else if (mode == 1) {
            for (int x = 0; x < gridSizeX; x++) {
                for (int y = 0; y < gridSizeY; y++) {
                    gameGrid[x][y] = gameGridMirror[x][y];
                }
            }
        }
        return ;
    }

    // INTEGRITY VERIFIED
    public static int countNeighbors (int x, int y) {
        int aliveCount = 0;
        
        for (int subX = x-1; subX <= x+1; subX++) {
            for (int subY = y-1; subY <= y+1; subY++) {
                if (!(subX == x && subY == y)) {
                    try {
                        if (gameGrid[subX][subY] == aliveBlock) {
                            aliveCount++;
                        } 
                    } catch (Exception e) {
                        // System.out.println(e);
                    }
                }
            }
        }
        // System.out.println("I'm alive!");
        return aliveCount;
    }

    public static void graphicEngine() {
        clearScreen();

        for (int x = 0; x < gridSizeX; x++) {
            for (int y = 0; y < gridSizeX; y++) {
                if (hideDeaths && gameGrid[x][y] == deadBlock) {
                    System.out.printf("%c ", buildingBlock);
                } else {
                    System.out.printf("%c ", gameGrid[x][y]);
                }

            }
            System.out.printf("\n");
        }
        // System.out.println("____");
    }

    // WIP / FOR TESTING
    public static void runaway() {
        int runawayX, runawayY, memX, memY;
        memX = memY = runawayX = runawayY = 0;

        gameGrid[memX][memY] = '.';

        memX = runawayX = (int) (Math.random() * gridSizeX - 1);
        memY = runawayY = (int) (Math.random() * gridSizeX - 1);

        gameGrid[runawayX][runawayY] = 'X';
    }

    // https://stackoverflow.com/posts/32295974/revisions
    public static void clearScreen() {  
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }  
}
