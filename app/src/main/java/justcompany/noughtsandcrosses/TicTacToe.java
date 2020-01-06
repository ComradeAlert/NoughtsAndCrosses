package justcompany.noughtsandcrosses;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

class TicTacToe {

    private Map<Integer, Status> field = new HashMap<>();
    private List<Integer> clearCells = new ArrayList<>();
    private int [] canvas = {0,0,0,
                             0,0,0,
                             0,0,0};

    TicTacToe(List<Integer> btns) {
        for (Integer id : btns) {
            field.put(id, Status.CLEAR);
        }
        clearCells.addAll(btns);
    }

    Status getButtonStatus(Integer idButton) {
        return cells.get(idButton).status;
    }

    void setStatus(Integer id, Status status) {
        field.put(id, status);
        clearCells.remove(id);
    }

    Integer idButtonForComputerTurn() {
        Random rnd = new Random();
        Integer idBttnForTurn = clearCells.get(rnd.nextInt(clearCells.size()));
        setStatus(idBttnForTurn, Status.NOUGHT);
        return idBttnForTurn;
    }

    boolean canDoTurn() {
        boolean rsl = true;
        if (clearCells.size() == 0) {
            rsl = false;
        }
        return rsl;
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