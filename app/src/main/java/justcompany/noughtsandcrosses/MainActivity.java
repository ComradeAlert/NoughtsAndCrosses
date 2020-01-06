package justcompany.noughtsandcrosses;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private TicTacToe game = new TicTacToe(
            Arrays.asList(
                    R.id.button11, R.id.button12, R.id.button13,
                    R.id.button21, R.id.button22, R.id.button23,
                    R.id.button31, R.id.button32, R.id.button33
            ));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void answer(View view) {
        Button btn = (Button) view;
        Status status = game.getButtonStatus(btn.getId());

        if (status.equals(Status.CLEAR)) {
            btn.setText("X");
            game.setStatus(btn.getId(), Status.CROSS);
            if (game.thisMoveLedToVictory(btn.getId())) {
                msgWin("Humanoid");
            }
            else {
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
        btn.setText("O");
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
                        game = new TicTacToe(
                                Arrays.asList(
                                        R.id.button11, R.id.button12, R.id.button13,
                                        R.id.button21, R.id.button22, R.id.button23,
                                        R.id.button31, R.id.button32, R.id.button33
                                ));
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}