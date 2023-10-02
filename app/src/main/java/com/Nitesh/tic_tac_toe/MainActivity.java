package com.Nitesh.tic_tac_toe;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Button[][] buttons = new Button[3][3];
    private boolean player1Turn = true;
    Button b1;
    TextView t1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        b1=(Button)findViewById(R.id.button_reset);
        t1=(TextView)findViewById(R.id.textView2);
        initializeButtons();
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        buttons[i][j].setText("");
                    }

                }
                t1.setText("");
            }
        });

    }


    // Initialize the buttons and set their onClickListener
    private void initializeButtons() {
        GridLayout gridLayout = findViewById(R.id.gridLayout);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String buttonID = "button" + i + j;
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                buttons[i][j] = findViewById(resID);
                buttons[i][j].setOnClickListener(this::onCellClick);
            }
        }
    }

    // Handle button clicks
    public void onCellClick(View view) {
        Button button = (Button) view;
        if (button.getText().toString().equals("")) {
            if (player1Turn) {
                button.setText("X");
                check_win();
            } else {
                button.setText("O");
                check_win();
            }
            player1Turn = !player1Turn;
        }
    }
    // Check win condition.
    public void check_win() {
        // Check rows for a win
        for (int i = 0; i < 3; i++) {
            if (buttons[i][0].getText().equals(buttons[i][1].getText()) &&
                    buttons[i][1].getText().equals(buttons[i][2].getText()) &&
                    !buttons[i][0].getText().toString().isEmpty()) {

                String z=buttons[i][0].getText().toString();
                t1.setText(z+" Wins the match");
                return;
            }
        }

        // Check columns for a win
        for (int j = 0; j < 3; j++) {
            if (buttons[0][j].getText().equals(buttons[1][j].getText()) &&
                    buttons[1][j].getText().equals(buttons[2][j].getText()) &&
                    !buttons[0][j].getText().toString().isEmpty()) {
                String z=buttons[0][j].getText().toString();
                t1.setText(z+" Wins the match");
                return;
            }
        }

        // Check diagonals for a win
        if (buttons[0][0].getText().equals(buttons[1][1].getText()) &&
                buttons[1][1].getText().equals(buttons[2][2].getText()) &&
                !buttons[0][0].getText().toString().isEmpty()) {
            String z=buttons[1][1].getText().toString();
            t1.setText(z+" Wins the match");
            return;
        }

        if (buttons[0][2].getText().equals(buttons[1][1].getText()) &&
                buttons[1][1].getText().equals(buttons[2][0].getText()) &&
                !buttons[0][2].getText().toString().isEmpty()) {
            String z=buttons[0][2].getText().toString();
            t1.setText(z+" Wins the match");
            return;
        }

        // No winner, check for a tie
        boolean isTie = true;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j].getText().toString().isEmpty()) {
                    isTie = false;
                    break;
                }
            }
        }

        if (isTie) {
            t1.setText("Match Ties");
        }
    }



}
