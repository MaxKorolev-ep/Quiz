package space.korolev.quiz;

import android.os.Handler;
import android.widget.TextView;

public class LevelTimer {
    public int seconds;
    public boolean running;
    public String time, lvlsc, descr, finalstr;
    TextView tv_levelTime;

    LevelTimer(TextView tv_lvlTime, String levelsucces, String lvldescr, boolean run) {
        tv_levelTime=tv_lvlTime;
        descr=lvldescr;
        running=run;
        lvlsc=levelsucces;
    }

    public void resetSeconds()
    {
        seconds=0;
    }


    public int getSeconds()
    {
        return seconds;
    }

    String runTimer() {
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours =seconds/3600;
                int minutes =(seconds%3600)/60;
                int sec =seconds%60;
                time = String.format("%d:%02d:%02d", hours, minutes, sec);
                tv_levelTime.setText(time);
                if (running)
                {
                    seconds++;
                }
                handler.postDelayed(this,1000);
            }

        });
        finalstr=(lvlsc  + time +"\n"+descr);
        return finalstr;
    }
}
