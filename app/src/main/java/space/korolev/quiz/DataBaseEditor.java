package space.korolev.quiz;

import android.content.Context;
import android.util.Log;

import database.AppDataBase;
import database.AppExecutor;
import database.Client;

public  class DataBaseEditor  {
    private String name, level, time;
  public  int time_sec, item_id, pos, prev_sec, lastchance, aaaaa;
    private AppDataBase mydb;
    private Context context;


    public DataBaseEditor(int id, int position, String tv_name, String tv_level, int seconds, Context con)
    {
        item_id=id;
        name = tv_name;
        level = tv_level;
        int hours =seconds/3600;
        int minutes =(seconds%3600)/60;
        int sec =seconds%60;
        time = String.format("%d:%02d:%02d", hours, minutes, sec);
        time_sec = seconds;
        context=con;
        pos=position;

        init();

    }

    public void init() {
        mydb = AppDataBase.getInstance(context);
        AppExecutor.getInstance().getMainIO().execute(new Runnable() {
            @Override
            public void run() {
                AppExecutor.getInstance().getDiscIO().execute(new Runnable() {
                    @Override
                    public void run() {
                        prev_sec = mydb.clientDAO().getClientRank().get(pos).getSeconds1();
                        Log.d ("mydb", "prevsev  " + prev_sec);
                        if (prev_sec>time_sec)    updateDb();
                    }
                });
            }
        });
    }


    private void updateDb() {
        Log.d ("mydb", "yes  " + prev_sec);
            mydb = AppDataBase.getInstance(context);
            AppExecutor.getInstance().getMainIO().execute(new Runnable() {
                @Override
                public void run() {
                    AppExecutor.getInstance().getDiscIO().execute(new Runnable() {
                        @Override
                        public void run() {
                            Client client = new Client(item_id, time_sec, name, level, time);
                            mydb.clientDAO().updateClient(client);
                        }
                    });
                }
            });
        }
    }

