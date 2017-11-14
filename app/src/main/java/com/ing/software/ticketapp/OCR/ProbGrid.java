package com.ing.software.ticketapp.OCR;


import java.util.HashMap;

/**
 * Static class containing grids for probability regions (WIP)
 * @author Michelon
 */
public class ProbGrid {

    static final int GRIDCOUNT = 2; //number of grids
    static final String ratio16x9 = "16x9";
    static final String ratio16x7 = "16x7";
    static HashMap<Double, String> gridMap = new HashMap(GRIDCOUNT);
    static HashMap<String, Integer[][]> amountMap = new HashMap(GRIDCOUNT);
    static final Integer[][] AmountGrid16x9 = {
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {40, 50, 30, 10, 0, 0, 0, 0, 0},
            {60, 60, 50, 30, 10, 0, 0, 0, 0},
            {60, 70, 60, 40, 20, 0, 0, 0, 0},
            {40, 50, 30, 20, 0, 0, 0, 0, 0},
            {30, 40, 20, 10, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0}
    };
    static final Integer[][] AmountGrid16x7 = {
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0},
            {40, 50, 30, 10, 0, 0, 0},
            {60, 60, 50, 30, 10, 0, 0},
            {60, 70, 60, 40, 20, 0, 0},
            {40, 50, 30, 20, 0, 0, 0},
            {30, 40, 20, 10, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0}
    };

    static {
        //Key in gridMap is Math.floor(height/width*100)/100 of corresponding grid
        gridMap.put(1.77, ratio16x9);
        gridMap.put(2.28, ratio16x7);
        amountMap.put(ratio16x9, AmountGrid16x9);
        amountMap.put(ratio16x7, AmountGrid16x7);
    }
}
