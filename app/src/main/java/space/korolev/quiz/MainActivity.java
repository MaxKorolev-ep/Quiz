package space.korolev.quiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import database.AppDataBase;
import database.Client;
import database.DataAdapter;


public class MainActivity extends AppCompatActivity {
    private long backPressedTime;
    private Toast backToast;
    private AppDataBase mydb;
    private DataAdapter dbAdapter;
    private List<Client> listClient;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      //  init();
        //развернуть игру на весь экран - начало
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //развернуть игру на весь экран - конец

        Button buttonStart = (Button)findViewById(R.id.btn_start);
        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               try
                {
                    Intent intent = new Intent(MainActivity.this,GameLevels.class);
                    startActivity(intent);finish();
                }
                catch (Exception e)
                {

                }
            }
        });

    }





    //system btn BACK Start
    @Override
    public void onBackPressed()
    {

        if (backPressedTime + 2000 > System.currentTimeMillis())
        {
            backToast.cancel();
            super.onBackPressed();
            return;
        }
        else
        {
            backToast = Toast.makeText(getBaseContext(),"Нажмите ещё раз, чтобы выйти",Toast.LENGTH_LONG);
            backToast.show();
        }
        backPressedTime = System.currentTimeMillis();
    }
    //system btn BACK End

}