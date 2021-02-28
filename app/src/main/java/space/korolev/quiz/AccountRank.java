package space.korolev.quiz;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import database.AppDataBase;
import database.AppExecutor;
import database.Client;
import database.DataAdapter;

public class AccountRank extends AppCompatActivity {

private AppDataBase mydb;
    private TextView name, level, time;
    private RecyclerView recycler;
  //  public DataBaseEditor dbEditor;
    private DataAdapter dbAdapter;
    private List<Client> listClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testdblayot);
        name = findViewById(R.id.tv_name);
        level = findViewById(R.id.tv_level);
        time = findViewById(R.id.tv_time);
        recycler = findViewById(R.id.r_view);

       // dbEditor = new DataBaseEditor("String1", "String2", "String3", this);
        init();

    }



    private void init()
    {
        recycler = findViewById(R.id.r_view);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        mydb = AppDataBase.getInstance(getApplicationContext());
        AppExecutor.getInstance().getDiscIO().execute(new Runnable() {
            @Override
            public void run() {
                listClient = mydb.clientDAO().getClientRank();
                AppExecutor.getInstance().getMainIO().execute(new Runnable() {


                    @Override
                    public void run() {
                        dbAdapter = new DataAdapter(listClient);
                        recycler.setAdapter(dbAdapter);
                    }
                });
            }
        });
    }
}

