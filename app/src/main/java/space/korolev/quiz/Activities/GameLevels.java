package space.korolev.quiz.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import database.AppDataBase;
import space.korolev.quiz.AccountRank;
import space.korolev.quiz.R;

public class GameLevels extends AppCompatActivity {

    private AppDataBase mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_levels);

        //развернуть игру на весь экран - начало
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //развернуть игру на весь экран - конец


        Button buttonBack = (Button) findViewById(R.id.btn_back);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(GameLevels.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } catch (Exception e) {

                }
            }
        });

        Button buttonRank = (Button) findViewById(R.id.btn_rank);
        buttonRank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(GameLevels.this, AccountRank.class);
                    startActivity(intent);
                    finish();
                } catch (Exception e) {

                }
            }
        });


        //btn 1 lvl = start
        Button textView1 = (Button) findViewById(R.id.textView1);
        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(GameLevels.this, Level1.class);
                    startActivity(intent);
                    finish();
                } catch (Exception e) {

                }
            }
        });
        //btn 1 lvl = end

        //btn 2 lvl = start
        Button textView2 = (Button) findViewById(R.id.textView2);
        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(GameLevels.this, Level2.class);
                    startActivity(intent);
                    finish();
                } catch (Exception e) {

                }
            }
        });
        //btn 2 lvl = end

        //btn 3 lvl = start
        Button textView3 = (Button) findViewById(R.id.textView3);
        textView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(GameLevels.this, Level3.class);
                    startActivity(intent);
                    finish();
                } catch (Exception e) {

                }
            }
        });
        //btn 3 lvl = end

        //btn 4 lvl = start
        Button textView4 = (Button) findViewById(R.id.textView4);
        textView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    Intent intent = new Intent(GameLevels.this, Level4.class);
                    startActivity(intent);
                    finish();

                } catch (Exception e) {

                }
            }
        });
        //btn 4 lvl = end

        //btn 5 lvl = start
        Button textView5 = (Button) findViewById(R.id.textView5);
        textView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    Intent intent = new Intent(GameLevels.this, Level5.class);
                    startActivity(intent);
                    finish();

                } catch (Exception e) {

                }
            }
        });
        //btn 5 lvl = end

        //btn 6 lvl = start
        Button button6_europe = (Button) findViewById(R.id.button6_europe);
        button6_europe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    Intent intent = new Intent(GameLevels.this, GeoLvl1.class);
                    startActivity(intent);
                    finish();

                } catch (Exception e) {

                }
            }
        });
        //btn 6 lvl = end

        //btn 7 lvl = start
        Button button7_asia = (Button) findViewById(R.id.button7_asia);
        button7_asia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    Intent intent = new Intent(GameLevels.this, GeoLvl2.class);
                    startActivity(intent);
                    finish();

                } catch (Exception e) {

                }
            }
        });
        //btn 7 lvl = end

        //btn 8 lvl = start
        Button button8_africa = (Button) findViewById(R.id.button8_africa);
        button8_africa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    Intent intent = new Intent(GameLevels.this, GeoLvl3.class);
                    startActivity(intent);
                    finish();

                } catch (Exception e) {

                }
            }
        });
        //btn 8 lvl = end

        //btn 9 lvl = start
        Button button9_northAmerica = (Button) findViewById(R.id.button9_northAmerica);
        button9_northAmerica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    Intent intent = new Intent(GameLevels.this, GeoLvl4.class);
                    startActivity(intent);
                    finish();

                } catch (Exception e) {

                }
            }
        });
        //btn 9 lvl = end

        //btn 10 lvl = start
        Button button10_southAmerica = (Button) findViewById(R.id.button10_southAmerica);
        button10_southAmerica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    Intent intent = new Intent(GameLevels.this, GeoLvl5.class);
                    startActivity(intent);
                    finish();

                } catch (Exception e) {

                }
            }
        });
        //btn 10 lvl = end
/*
        //btn 11 lvl = starta
        Button textView11 = (Button) findViewById(R.id.textView11);
        textView11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    Intent intent = new Intent(GameLevels.this, GeoLvl6.class);
                    startActivity(intent);
                    finish();

                } catch (Exception e) {

                }
            }
        });
*/

    }


    //system btn BACK Start
    @Override
    public void onBackPressed() {


        try {
            Intent intent = new Intent(GameLevels.this, MainActivity.class);
            startActivity(intent);
            finish();
        } catch (Exception e) {

        }
    }
    //system btn BACK End

}