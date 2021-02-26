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
import database.AppExecutor;
import database.Client;

public class GameLevels extends AppCompatActivity {

    private AppDataBase mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_levels);
        init();
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

    private void init()
    {

        mydb = AppDataBase.getInstance(getApplicationContext());
        AppExecutor.getInstance().getDiscIO().execute(new Runnable() {
            @Override
            public void run() {
                AppExecutor.getInstance().getMainIO().execute(new Runnable() {
                    @Override
                    public void run() {
                        Client client = new Client("Max", "Level 4", "00:01:03");
                        mydb.clientDAO().insertClient(client);
                    }
                });
            }
        });
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