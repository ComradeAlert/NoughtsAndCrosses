package justcompany.noughtsandcrosses;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

class TicTacToe {

    private Map<Integer,Cell> cells = new HashMap<>();
    private List<Integer> clearCells = new ArrayList<>();
    private int [] canvas = {0,0,0,
                             0,0,0,
                             0,0,0};

    TicTacToe(List<Integer> btns) {
        int numberCell = 0;

        for (Integer idButton : btns) {
            Cell cell = new Cell();
            cell.number = numberCell++;
            cell.status = Status.CLEAR;

            cells.put(idButton, cell);
        }

        clearCells.addAll(btns);
    }

    Status getButtonStatus(Integer idButton) {
        return cells.get(idButton).status;
    }

    void setStatus(Integer idButton, Status status) {
        Cell cell = cells.get(idButton);
        cell.status = status;
        cells.put(idButton, cell);
        canvas[cell.number] = status.value;
        if (status.equals(Status.CROSS) || status.equals(Status.NOUGHT)) {
            clearCells.remove(idButton);
        }
    }

    Integer idButtonForComputerTurn() {
        Random rnd = new Random();
        Integer idButtonForTurn = clearCells.get(rnd.nextInt(clearCells.size()));
        return idButtonForTurn;
    }

    Boolean canDoTurn() {
        Boolean canDoTurn = true;
        if (clearCells.size() == 0) {
            canDoTurn = false;
        }
        return canDoTurn;
    }

    Boolean thisMoveLedToVictory(Integer idButtonFromLastTurn) {
        int n = cells.get(idButtonFromLastTurn).number;
        // 0 1 2
        // 3 4 5
        // 6 7 8
        //поиск совпадений по горизонтали
        int row = n - n%3; //номер строки - проверяем только её
        if (canvas[row]==canvas[row+1] && canvas[row+1]==canvas[row+2]) {
            return true;
        }
        //поиск совпадений по вертикали
        int column = n%3; //номер столбца - проверяем только его
        if (canvas[column]==canvas[column+3] && canvas[column+3]==canvas[column+6]) {
            return true;
        }
        //мы здесь, значит, предыдущий поиск не дал положительного результата
        //если значение n находится на одной из граней - возвращаем false
        if (n%2 != 0) {
            return false;
        }
        //проверяем принадлежит ли левой диагонали значение
        if (n%4 == 0){
            //проверяем есть ли совпадения на левой диагонали
            if (canvas[0] == canvas[4] && canvas[4] == canvas[8]) {
                return true;
            }
            if (n != 4) {
                return false;
            }
        }
        //проверяем есть ли совпадения на правой диагонали
        return canvas[2] == canvas[4] && canvas[4] == canvas[6];
    }
}