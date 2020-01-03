package justcompany.noughtsandcrosses;

import java.util.List;
import java.util.Map;

public class TicTacToe {

    private Map<Integer, Status> field;

    TicTacToe(List<Integer> btns) {
        for (Integer i : btns) {
            field.put(i, Status.CLEAR);
        }
    }

    Status getStatus(Integer id) {
        return field.get(id);
    }

    public void setStatus(Integer id, Status status) {
        field.put(id, status);
    }
}
