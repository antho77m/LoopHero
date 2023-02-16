package fr.iut.zen.game;


import fr.iut.zen.game.Cells.Cell;

import java.util.ArrayList;

//tools for loop Hero
public class Tools {

    public static float roundNumber(float number){
        number*=10;
        number = Math.round(number);
        number/=10;
        return number;
    }

    public static boolean posInMatrix(Cell[][] cells , GridPosition gridPosition){
        return gridPosition.column() < cells[0].length && gridPosition.column() > 0
                && gridPosition.line() < cells.length && gridPosition.line() > 0;
    }

    public static ArrayList<Cell> getAdjacentCell(Cell[][] matrix,GridPosition gridPosition){
        ArrayList<Cell> adjacentCell = new ArrayList<>();

        GridPosition leftCell = new GridPosition(gridPosition.line() - 1, gridPosition.column());
        GridPosition rightCell = new GridPosition(gridPosition.line() + 1, gridPosition.column());
        GridPosition upperCell = new GridPosition(gridPosition.line() , gridPosition.column()-1);
        GridPosition bottomCell = new GridPosition(gridPosition.line() , gridPosition.column()+1);

        if (Tools.posInMatrix(matrix,leftCell))
            adjacentCell.add(matrix[leftCell.line()][leftCell.column()]);
        if (Tools.posInMatrix(matrix,rightCell))
            adjacentCell.add(matrix[rightCell.line()][rightCell.column()]);
        if (Tools.posInMatrix(matrix,upperCell))
            adjacentCell.add(matrix[upperCell.line()][upperCell.column()]);
        if (Tools.posInMatrix(matrix,bottomCell))
            adjacentCell.add(matrix[bottomCell.line()][bottomCell.column()]);

        return adjacentCell;
    }



}
