package space.korolev.quiz;

import android.content.Context;

import database.AppDataBase;
import database.AppExecutor;
import database.Client;

public  class DataBaseEditor  {
    private String name, level, time;
    int time_sec;
    private AppDataBase mydb;
    private Context context;
    public DataBaseEditor(String tv_name, String tv_level, int seconds, Context con)
    {
        name = tv_name;
        level = tv_level;
        int hours =seconds/3600;
        int minutes =(seconds%3600)/60;
        int sec =seconds%60;
        time = String.format("%d:%02d:%02d", hours, minutes, sec);
        time_sec = seconds;
        context=con;
        init();
    }

    private void init()
    {
        mydb = AppDataBase.getInstance(context);
        AppExecutor.getInstance().getMainIO().execute(new Runnable() {
            @Override
            public void run() {
                AppExecutor.getInstance().getDiscIO().execute(new Runnable() {
                    @Override
                    public void run() {
                        Client client = new Client(time_sec, name, level, time);
                        mydb.clientDAO().insertClient(client);
                    }
                });
            }
        });
    }

}
