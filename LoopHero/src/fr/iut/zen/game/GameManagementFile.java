package fr.iut.zen.game;

import fr.iut.zen.game.MainPart.LoopHeroData;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

public class GameManagementFile {

    private static final String nameSave = "gameSave";


    private static GridPosition readStringGridPosition(String line) {
        String[] split = line.split(",");
        return new GridPosition(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
    }

    public static List<GridPosition> loadPaths(Path path, int columns, int lines)  {
        ArrayList<GridPosition> gridPositions = new ArrayList<>();
        try(BufferedReader bufferedReader = Files.newBufferedReader(path)){
            String line;
            while ((line = bufferedReader.readLine())!= null) {
                gridPositions.add(readStringGridPosition(line));
            }
            verifyGridPositions(gridPositions, columns, lines);

            return gridPositions;
        }
        catch (Exception e){

            System.err.println("Error for loading the game : "+e.getMessage());
            return List.of(new GridPosition(5, 2), new GridPosition(4, 2),
                    new GridPosition(4, 3), new GridPosition(3, 3), new GridPosition(3, 4), new GridPosition(3, 5),
                    new GridPosition(2, 5), new GridPosition(2, 6), new GridPosition(2, 7), new GridPosition(2, 8),
                    new GridPosition(2, 9), new GridPosition(3, 9), new GridPosition(3, 10), new GridPosition(3, 11),
                    new GridPosition(4, 11), new GridPosition(4, 12), new GridPosition(5, 12), new GridPosition(6, 12),
                    new GridPosition(6, 11), new GridPosition(6, 10), new GridPosition(7, 10), new GridPosition(7, 9),
                    new GridPosition(7, 8), new GridPosition(8, 8), new GridPosition(8, 7), new GridPosition(8, 6),
                    new GridPosition(9, 6), new GridPosition(9, 5), new GridPosition(9, 4), new GridPosition(9, 3),
                    new GridPosition(8, 3), new GridPosition(7, 3), new GridPosition(7, 2), new GridPosition(6, 2));
        }
    }

    private static void verifyGridPositions(ArrayList<GridPosition> gridPositions, int columns, int lines) {

        if (new HashSet<>(gridPositions).size() != gridPositions.size() || gridPositions.size() != 34) {
            throw new IllegalArgumentException("The game is not valid");
        }

        for (GridPosition gridPosition : gridPositions) {
            if (gridPosition.column() < 0 || gridPosition.column() >= columns) {
                throw new IllegalArgumentException("The grid position " + gridPosition + " is not valid");
            }
            if (gridPosition.line() < 0 || gridPosition.line() >= lines) {
                throw new IllegalArgumentException("The grid position " + gridPosition + " is not valid");
            }
        }

        //verify if the grid position is adjacent to the previous one

        for (int i = 1; i < gridPositions.size(); i++) {
            if (!gridPositions.get(i).isAdjacent(gridPositions.get(i - 1))) {
                throw new IllegalArgumentException("The grid position " + gridPositions.get(i) + " is not adjacent to the previous one");
            }
        }
        if (!gridPositions.get(0).isAdjacent(gridPositions.get(gridPositions.size()-1))) {
            throw new IllegalArgumentException("The first grid position " + gridPositions.get(0) + " is not adjacent to the last");
        }
    }

    public static void SaveGame(LoopHeroData loopHeroData) {
        Objects.requireNonNull(loopHeroData);
        Path path = Path.of(nameSave);
        try (OutputStream back = Files.newOutputStream(path);
             ObjectOutputStream out = new ObjectOutputStream(back)){
            out.writeObject(loopHeroData); // sauvegarde
        } catch (IOException e) {
            System.err.println("Error for saving the game : "+e.getMessage());
        }

    }

    public static LoopHeroData LoadGame() {
        Path path = Path.of(nameSave);
        try( InputStream back = Files.newInputStream(path);
             ObjectInputStream in = new ObjectInputStream(back)){
            return (LoopHeroData) in.readObject();
        } catch (Exception e) {
            System.err.println("Error for load the game : "+e.getMessage());
        }
        return null;
    }


}
