package justcompany.noughtsandcrosses;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private TicTacToe field = new TicTacToe(
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
        Status status = field.getStatus(btn.getId());

        if (status.equals(Status.CLEAR)){
            btn.setText("X");
            field.setStatus(btn.getId(), Status.CROSS);
        }

        if (status.equals(Status.CROSS)){
            btn.setText("O");
            field.setStatus(btn.getId(), Status.NOUGHT);
        }
    }
}
