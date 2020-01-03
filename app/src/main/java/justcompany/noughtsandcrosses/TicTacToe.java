package justcompany.noughtsandcrosses;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

class TicTacToe {

    private Map<Integer, Status> field = new HashMap<>();

    TicTacToe(List<Integer> btns) {
        for (Integer i : btns) {
            field.put(i, Status.CLEAR);
        }
    }

    Status getStatus(Integer id) {
        return field.get(id);
    }

    void setStatus(Integer id, Status status) {
        field.put(id, status);
    }
}
