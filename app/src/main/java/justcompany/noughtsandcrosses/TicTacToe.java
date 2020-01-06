package justcompany.noughtsandcrosses;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

class TicTacToe {

    private Map<Integer, Status> field = new HashMap<>();
    private List<Integer> clearCells = new ArrayList<>();

    TicTacToe(List<Integer> btns) {
        for (Integer id : btns) {
            field.put(id, Status.CLEAR);
        }
        clearCells.addAll(btns);
    }

    Status getStatus(Integer id) {
        return field.get(id);
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
}