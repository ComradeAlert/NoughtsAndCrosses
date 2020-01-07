package justcompany.noughtsandcrosses;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    private TicTacToe game = new TicTacToe(Arrays.asList(buttons));

    private static final String CLEAR = "";
    private static final String CROSS = "X";
    private static final String NOUGHT = "O";

    private ArrayList<Integer> clearCells = new ArrayList<>();
    private ArrayList<Integer> crossCells = new ArrayList<>();
    private ArrayList<Integer> noughtCells = new ArrayList<>();

    static Integer[] buttons = {
            R.id.button11, R.id.button12, R.id.button13,
            R.id.button21, R.id.button22, R.id.button23,
            R.id.button31, R.id.button32, R.id.button33};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        load(savedInstanceState);
        Log.d(MainActivity.class.getName(), "onCreate");
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        for (Integer idButton : buttons) {
            Status status = game.getButtonStatus(idButton);
            switch (status) {
                case CLEAR:
                    clearCells.add(idButton);
                    break;
                case CROSS:
                    crossCells.add(idButton);
                    break;
                case NOUGHT:
                    noughtCells.add(idButton);
                    break;
            }
        }

        outState.putIntegerArrayList(CLEAR, clearCells);
        outState.putIntegerArrayList(CROSS, crossCells);
        outState.putIntegerArrayList(NOUGHT, noughtCells);

        Log.d(MainActivity.class.getName(), "onSaveInstanceState");
    }

    private void load(Bundle state) {
        if (state != null) {
            Button button;
            for (Integer idButton : state.getIntegerArrayList(CLEAR)) {
                button = findViewById(idButton);
                button.setText(CLEAR);
                game.setStatus(idButton, Status.CLEAR);
            }
            for (Integer idButton : state.getIntegerArrayList(CROSS)) {
                button = findViewById(idButton);
                button.setText(CROSS);
                game.setStatus(idButton, Status.CROSS);
            }
            for (Integer idButton : state.getIntegerArrayList(NOUGHT)) {
                button = findViewById(idButton);
                button.setText(NOUGHT);
                game.setStatus(idButton, Status.NOUGHT);
            }
        }
    }

    public void answer(View view) {
        Button btn = (Button) view;
        Status status = game.getButtonStatus(btn.getId());

        if (status.equals(Status.CLEAR)) {
            btn.setText(CROSS);
            game.setStatus(btn.getId(), Status.CROSS);
            if (game.thisMoveLedToVictory(btn.getId())) {
                msgWin("Humanoid");
            } else {
                if (game.canDoTurn()) {
                    computerTurn();
                }
            }
        }

        if (game.canDoTurn() && (status.equals(Status.CROSS) || status.equals(Status.NOUGHT))) {
            msgMistake();
        }

        if (!(game.canDoTurn()) && !(game.thisMoveLedToVictory(btn.getId()))) {
            msgGameOver();
        }
    }

    private void computerTurn() {
        Button btn = findViewById(game.idButtonForComputerTurn());
        btn.setText(NOUGHT);
        game.setStatus(btn.getId(), Status.NOUGHT);
        if (game.thisMoveLedToVictory(btn.getId())) {
            msgWin("Computer");
        }
    }

    private void msgMistake() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
        alertDialogBuilder.setTitle("Sorry. You are mistaken.");
        alertDialogBuilder
                .setMessage("Так нельзя =)")
                .setCancelable(true)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    private void msgGameOver() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
        alertDialogBuilder.setTitle("GameOver");
        alertDialogBuilder
                .setMessage("Not Clear Cells =)")
                .setCancelable(false)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        newGame();
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    private void msgWin(String player) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
        alertDialogBuilder.setTitle("GameOver");
        alertDialogBuilder
                .setMessage(player + " win =)")
                .setCancelable(false)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        newGame();
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    void newGame() {
        game = new TicTacToe(Arrays.asList(buttons));
        Button btn;

        for (Integer btnId : buttons) {
            btn = findViewById(btnId);
            btn.setText(CLEAR);
        }
    }
}