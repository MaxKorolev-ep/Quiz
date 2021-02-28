package space.korolev.quiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import database.AppDataBase;

public class GameLevels extends AppCompatActivity {

    private AppDataBase mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_levels);

        //развернуть игру на весь экран - начало
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //развернуть игру на весь экран - конец


        Button buttonBack = (Button)findViewById(R.id.btn_back);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try
                {
                    Intent intent = new Intent(GameLevels.this,MainActivity.class);
                    startActivity(intent);finish();
                }
                catch (Exception e)
                {

                }
            }
        });

        Button buttonRank = (Button)findViewById(R.id.btn_rank);
        buttonRank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try
                {
                    Intent intent = new Intent(GameLevels.this, AccountRank.class);
                    startActivity(intent);finish();
                }
                catch (Exception e)
                {

                }
            }
        });


        //btn 1 lvl = start
        TextView textView1 = (TextView)findViewById(R.id.textView1);
        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try
                {
                    Intent intent = new Intent(GameLevels.this,Level1.class);
                    startActivity(intent);finish();
                }
                catch (Exception e)
                {

                }
            }
        });
        //btn 1 lvl = end

        //btn 2 lvl = start
        TextView textView2 = (TextView)findViewById(R.id.textView2);
        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try
                {
                    Intent intent = new Intent(GameLevels.this,Level2.class);
                    startActivity(intent);finish();
                }
                catch (Exception e)
                {

                }
            }
        });
        //btn 2 lvl = end

        //btn 3 lvl = start
        TextView textView3 = (TextView)findViewById(R.id.textView3);
        textView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try
                {
                    Intent intent = new Intent(GameLevels.this,Level3.class);
                    startActivity(intent);finish();
                }
                catch (Exception e)
                {

                }
            }
        });
        //btn 3 lvl = end

        //btn 4 lvl = start
        TextView textView4 = (TextView)findViewById(R.id.textView4);
        textView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try
                {

                    Intent intent = new Intent(GameLevels.this,Level4.class);
                    startActivity(intent);finish();

                }
                catch (Exception e)
                {

                }
            }
        });
        //btn 4 lvl = end


    }


    //system btn BACK Start
    @Override
    public void onBackPressed()
    {


        try
        {
            Intent intent = new Intent(GameLevels.this,MainActivity.class);
            startActivity(intent);finish();
        }
        catch (Exception e)
        {

        }
    }
    //system btn BACK End

}