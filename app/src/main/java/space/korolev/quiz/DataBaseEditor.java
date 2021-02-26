package space.korolev.quiz;

import android.content.Context;

import database.AppDataBase;
import database.AppExecutor;
import database.Client;

public  class DataBaseEditor  {
    private String name, level, time;
    private AppDataBase mydb;
    private Context context;
    public DataBaseEditor(String tv_name, String tv_level, String tv_time, Context con)
    {
        name = tv_name;
        level = tv_level;
        time = tv_time;
        context=con;
        init();
    }

    private void init()
    {
        mydb = AppDataBase.getInstance(context);
        AppExecutor.getInstance().getDiscIO().execute(new Runnable() {
            @Override
            public void run() {
                AppExecutor.getInstance().getMainIO().execute(new Runnable() {
                    @Override
                    public void run() {
                        Client client = new Client("name", "level", "time");
                        mydb.clientDAO().insertClient(client);
                    }
                });
            }
        });
    }

}
