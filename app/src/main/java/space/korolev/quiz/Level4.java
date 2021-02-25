package space.korolev.quiz;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import database.AppDataBase;
import database.AppExecutor;
import database.Client;
import database.DataAdapter;

public class Level4 extends AppCompatActivity {

private AppDataBase mydb;
    private DataAdapter dbAdapter;
    private List<Client> listClient;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testdblayot);
        init();

    }



    private void init()
    {
        recyclerView = findViewById(R.id.r_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mydb = AppDataBase.getInstance(getApplicationContext());
        AppExecutor.getInstance().getDiscIO().execute(new Runnable() {
            @Override
            public void run() {
                listClient = mydb.clientDAO().getClientRank();
                AppExecutor.getInstance().getMainIO().execute(new Runnable() {
                    @Override
                    public void run() {
                        dbAdapter = new DataAdapter(listClient);
                         recyclerView.setAdapter(dbAdapter);
                    }
                });
            }
        });
    }

}
