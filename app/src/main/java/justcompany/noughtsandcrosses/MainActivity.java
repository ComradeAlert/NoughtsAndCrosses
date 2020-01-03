package justcompany.noughtsandcrosses;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

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
}
