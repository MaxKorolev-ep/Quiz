package space.korolev.quiz.Activities;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import space.korolev.quiz.DataBaseEditor;
import space.korolev.quiz.LevelTimer;
import space.korolev.quiz.LogicCoutry4Lines;
import space.korolev.quiz.R;


public class GeoLvl1 extends AppCompatActivity {

    Dialog dialog;
    Dialog dialogEnd;

    public boolean timerStarted;
    public boolean timerFinish;
    public boolean timerCancel;
    private Toast backToast;

    public DataBaseEditor dbEditor;

    public RoundTimer roundTimer;
    public SetBackColor setBackColor;

    public boolean startLevel;
    public LevelTimer levelTimer;

    public int count = 0;// счетчик правильных ответов
    private long backPressedTime;

    public String resultText;

    //settings of timer
    public final long millisInFuture = 6000;
    public final long countDownInterval = 500;
    public final int progressScale = (int) millisInFuture / 100;

    //initialize View container
    ProgressBar pb_timeLeft;
    TextView tv_levels;
    TextView tv;
    public TextView tvdend;
    public TextView tv_levelTime;

    public TextView quest, var1, var2, var3, var4;

    public LogicCoutry4Lines logCountry;
    // add array of progress start\
    public int[] progress = {
            R.id.point1, R.id.point2, R.id.point3, R.id.point4, R.id.point5, R.id.point6, R.id.point7,
            R.id.point8, R.id.point9, R.id.point10, R.id.point11, R.id.point12, R.id.point13, R.id.point14, R.id.point15,
            R.id.point16, R.id.point17, R.id.point18, R.id.point19, R.id.point20,};
    // add array of progress end

    public String[] countries;
    public TextView[] vars;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.universal4line);
        quest = findViewById(R.id.text_setquest);
        var1 = findViewById(R.id.text_set1);
        var2 = findViewById(R.id.text_set2);
        var3 = findViewById(R.id.text_set3);
        var4 = findViewById(R.id.text_set4);
        countries = getResources().getStringArray(R.array.europeCountries);

        Context context = this;

        //initialize logic
        logCountry = new LogicCoutry4Lines(quest, var1, var2, var3, var4, countries);
        Log.d("Text", "Country" + quest.getText());
        resultText = logCountry.strResult;
        Log.d("Text", "City" + resultText);
        // add array of progress start\

        vars = new TextView[]{var1, var2, var3, var4};

        tv_levelTime = findViewById(R.id.tv_roundtime);
        tv_levels = findViewById(R.id.tx_levels);
        tv_levels.setText(R.string.level4);

        pb_timeLeft = findViewById(R.id.pb_TimeLeft);
        //timer settings
        roundTimer = new RoundTimer(millisInFuture, countDownInterval, pb_timeLeft, timerCancel);
        //______________________________

        //развернуть игру на весь экран - начало
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //развернуть игру на весь экран - конец

        //вызов диалогового окна в начале игры - начало
        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);//hide a title
        dialog.setContentView(R.layout.previewdialog);
        ImageView imgd = dialog.findViewById(R.id.previewimg1);
        imgd.setImageResource(R.drawable.previewlevel3img);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));//transparent background of dialog wnd
        dialog.setCancelable(false); //окно нельзя закрыть кропкой назад

        //кнопка, которая закрывает диалог окно - начало
        TextView btnclose = (TextView) dialog.findViewById(R.id.btnclose);
        btnclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //обработка нажатия кнопки - начало
                try {
                    Intent intent = new Intent(GeoLvl1.this, GameLevels.class);
                    startActivity(intent);
                    finish();
                } catch (Exception e) {
                }
                dialog.dismiss();//close dialog wnd
            }
            //обработка нажатия кнопки - конец
        });
        //кнопка, которая закрывает диалог окно - конец

        //кнопка, которая переходит к уровню - начало
        Button btncont = (Button) dialog.findViewById(R.id.btncontinue);
        btncont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //обработка нажатия кнопки - начало
                //LEVEL TIMER INITIALIZE
                startLevel = true;
                levelTimer = new LevelTimer(tv_levelTime, getResources().getString(R.string.levelsucces), getResources().getString(R.string.levelendtwo), startLevel);
                levelTimer.runTimer();
                //__________________________________________
                dialog.dismiss();//close dialog wnd


            }
            //обработка нажатия кнопки - конец
        });
        //кнопка, которая переходит к уровню - конец

        dialog.show();

        //вызов диалогового окна в конце игры - начало
        dialogEnd = new Dialog(this);
        dialogEnd.requestWindowFeature(Window.FEATURE_NO_TITLE);//hide a title
        dialogEnd.setContentView(R.layout.dialogend);
        tvdend = dialogEnd.findViewById(R.id.textdescrend1);
        tvdend.setText("ERROR");
        dialogEnd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));//transparent background of dialog wnd
        dialogEnd.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT);
        dialogEnd.setCancelable(false); //окно нельзя закрыть кропкой назад

        //кнопка, которая закрывает диалог окно - начало
        TextView btnclose2 = (TextView) dialogEnd.findViewById(R.id.btnclose);
        btnclose2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //обработка нажатия кнопки - начало
                try {
                    Intent intent = new Intent(GeoLvl1.this, GameLevels.class);
                    startActivity(intent);
                    finish();
                } catch (Exception e) {

                }
                dialogEnd.dismiss();//close dialog wnd
            }
            //обработка нажатия кнопки - конец
        });
        //кнопка, которая закрывает диалог окно - конец

        //кнопка, которая переходит к уровню - начало
        Button btncontEnd = (Button) dialogEnd.findViewById(R.id.btncontinue);
        btncontEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //обработка нажатия кнопки - начало
                try {
                    Intent intent = new Intent(GeoLvl1.this, GeoLvl2.class);
                    startActivity(intent);
                    finish();
                } catch (Exception e) {

                }
                dialogEnd.dismiss();//close dialog wnd
            }
            //обработка нажатия кнопки - конец
        });
        //кнопка, которая переходит к уровню - конец
        //___________________________________


        // bnt back start
        Button btn_back = (Button) findViewById(R.id.btn_lvl_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(GeoLvl1.this, GameLevels.class);
                    startActivity(intent);
                    finish();
                } catch (Exception e) {
                }
            }
        });


        //закрашиваем прогресс серым цветом
        for (int i = 0; i < 20; i++) {
            tv = findViewById(progress[i]);
            tv.setBackgroundResource(R.drawable.style_points);
        }


        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Log.d("COUNTR","Num  "+count);
                Log.d("Text", "" + resultText);
                switch (v.getId()) {
                    case R.id.text_set1:
                        if (var1.getText() == resultText) {
                            //                      var1.setTextColor(getColor(R.color.green80));
                            count = count + 1;
                        } else {
                            //                           var1.setTextColor(getColor(R.color.red80));
                            if (count > 2) count = count - 2;
                            else count = 0;
                        }
                        break;
                    case R.id.text_set2:
                        if (var2.getText() == resultText) {
                            //             var2.setBackground(getDrawable(R.drawable.btn_stroke_white_press_green)); //(R.drawable.btn_stroke_white_press_green);
                            count = count + 1;
                        } else {
                            if (count > 2) count = count - 2;
                            else count = 0;
                        }
                        break;
                    case R.id.text_set3:

                        if (var3.getText() == resultText) count = count + 1;
                        else {
                            if (count > 2) count = count - 2;
                            else count = 0;
                        }
                        break;
                    case R.id.text_set4:

                        if (var4.getText() == resultText) count = count + 1;
                        else {
                            if (count > 2) count = count - 2;
                            else count = 0;
                        }
                        break;
                }
                roundTimer.cancel();

                //закрашиваем прогресс серым цветом
                for (int i = 0; i < 20; i++) {
                    tv = findViewById(progress[i]);
                    tv.setBackgroundResource(R.drawable.style_points);
                }
                //закрашиваем правильные ответы зеленым
                for (int i = 0; i < count; i++) {
                    tv = findViewById(progress[i]);
                    tv.setBackgroundResource(R.drawable.style_points_green);
                }


                if (count == 20) {
                    tvdend.setText(levelTimer.runTimer());
                    //         dbEditor = new DataBaseEditor(1,1,"Madx", "Levsdfsdfsdfel1", levelTimer.getSeconds(), context);
                    startLevel = false;
                    levelTimer.resetSeconds();
                    dialogEnd.show();
                } else {
                    setBackColor = new SetBackColor(1000, 100, this);
                    setBackColor.start();
                    var1.setOnClickListener(null);
                    var2.setOnClickListener(null);
                    var3.setOnClickListener(null);
                    var4.setOnClickListener(null);
                }

                //    Log.d("COUNTER","Text:"+resultText);
                //       var1.setBackground(getDrawable(R.drawable.btn_stroke_white_press_blue));

            }

        };

        var1.setOnClickListener(onClickListener);
        var2.setOnClickListener(onClickListener);
        var3.setOnClickListener(onClickListener);
        var4.setOnClickListener(onClickListener);

    }


    class SetBackColor extends CountDownTimer {
        public View.OnClickListener onCLick;

        /**
         * @param millisInFuture    The number of millis in the future from the call
         *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
         *                          is called.
         * @param countDownInterval The interval along the way to receive
         *                          {@link #onTick(long)} callbacks.
         */

        public SetBackColor(long millisInFuture, long countDownInterval, View.OnClickListener v) {
            super(millisInFuture, countDownInterval);
            onCLick = v;
        }

        @Override
        public void onTick(long millisUntilFinished) {
            for (int i = 0; i < 4; i++) {
                if (vars[i].getText() == resultText)
                    vars[i].setBackground(getDrawable(R.drawable.style_btn_white_green_press));
                else vars[i].setBackground(getDrawable(R.drawable.style_btn_white_red_press));
            }

            timerFinish = false;
        }

        @Override
        public void onFinish() {
            for (int i = 0; i < 4; i++) {
                vars[i].setBackground(getDrawable(R.drawable.btn_stroke_white_press_blue));
            }
            var1.setOnClickListener(onCLick);
            var2.setOnClickListener(onCLick);
            var3.setOnClickListener(onCLick);
            var4.setOnClickListener(onCLick);
            logCountry = new LogicCoutry4Lines(quest, var1, var2, var3, var4, countries);
            resultText = logCountry.strResult;
            roundTimer.start();
            timerStarted = true;
        }
    }


    class RoundTimer extends CountDownTimer {
        final ProgressBar pb;


        /**
         * @param millisInFuture    The number of millis in the future from the call
         *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
         *                          is called.
         * @param countDownInterval The interval along the way to receive
         *                          {@link #onTick(long)} callbacks.
         * @param pb
         */

        public RoundTimer(long millisInFuture, long countDownInterval, ProgressBar pb, boolean cancelTimer) {
            super(millisInFuture, countDownInterval);
            this.pb = pb;
            if (cancelTimer) cancel();
        }

        @Override
        public void onTick(long millisUntilFinished) {
            timerFinish = false;
            pb.setProgress((int) millisUntilFinished / progressScale);
            if (millisUntilFinished < 2000) {
                pb.setProgressTintList(ColorStateList.valueOf(Color.RED));
            } else
                pb.setProgressTintList(ColorStateList.valueOf(Color.GREEN));
        }

        @Override
        public void onFinish() {

            count = count - 2;
            for (int i = 0; i <= 19; i++) {
                tv = findViewById(progress[i]);
                tv.setBackgroundResource(R.drawable.style_points);
            }
            //закрашиваем правильные ответы зеленым
            for (int i = 0; i < count; i++) {
                tv = findViewById(progress[i]);
                tv.setBackgroundResource(R.drawable.style_points_green);
            }
            //  logCountry = new LogicCoutry4Lines(quest, var1, var2, var3, var4, countries);
            //resultText=logCountry.strResult;
            roundTimer = new RoundTimer(millisInFuture, countDownInterval, pb_timeLeft, timerCancel);
            roundTimer.start();
            timerStarted = true;
        }
    }


    //system btn BACK Start
    @Override
    public void onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            try {
                backToast.cancel();
                Intent intent = new Intent(GeoLvl1.this, GameLevels.class);
                startActivity(intent);
                finish();
            } catch (Exception e) {

            }
        } else {
            backToast = Toast.makeText(getBaseContext(), "Нажмите ещё раз, чтобы выйти из уровня", Toast.LENGTH_LONG);
            backToast.show();
        }
        backPressedTime = System.currentTimeMillis();
    }
    //system btn BACK End

}


