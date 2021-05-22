package space.korolev.quiz.Activities;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

import database.AppDataBase;
import database.Client;
import space.korolev.quiz.Array;
import space.korolev.quiz.DataBaseEditor;
import space.korolev.quiz.LevelTimer;
import space.korolev.quiz.R;

public class Level1 extends AppCompatActivity {

    Dialog dialog;
  public   Dialog dialogEnd;
    public Client client;
    public AppDataBase mydb;
    public DataBaseEditor dbEditor;
    public boolean timerStarted;
    public boolean timerFinish;
    public boolean timerCancel;

    public RoundTimer roundTimer;


    public boolean startLevel;
    public LevelTimer levelTimer;

    public int prev_sec;
    public int numLeft;
    public int numRight;
    public Array array = new Array(); // создаем новый объекст из класса Array
    public Random random = new Random();// создаем генерация чисел
    public int count = 0;// счетчик правильных ответов

    private long backPressedTime;

    private Toast backToast;


    public final long millisInFuture = 3000;
    public final long countDownInterval = 300;
    public final int progressScale = (int)millisInFuture/100;

    //initialize View container
     ProgressBar pb_timeLeft;
     ImageView img_left;
     ImageView img_right;
    public TextView tv_levelTime;
     TextView tv;
   public  TextView tvdend;
     TextView tx_levels;

    //_________________________



    @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.universal);
          //Устанавливаем текст tx_level
        Context context = Level1.this;

        // add array of progress start\
         final int[] progress = {
                R.id.point1,R.id.point2, R.id.point3, R.id.point4, R.id.point5, R.id.point6, R.id.point7,
                R.id.point8, R.id.point9,R.id.point10,R.id.point11,R.id.point12,R.id.point13,R.id.point14,R.id.point15,
                R.id.point16,R.id.point17,R.id.point18,R.id.point19,R.id.point20,};
        // add array of progress end


        //add animation start
         final Animation a = AnimationUtils.loadAnimation(Level1.this, R.anim.alpha);
        //add animation end

        tv_levelTime = findViewById(R.id.tv_roundtime);
        tx_levels = findViewById(R.id.tx_levels);
        tx_levels.setText(R.string.level1);


        pb_timeLeft = findViewById(R.id.pb_TimeLeft);
        //timer settings
       roundTimer = new RoundTimer(millisInFuture, countDownInterval, pb_timeLeft,timerCancel);


        //______________________________


        //путь к картинкам
        img_left = findViewById(R.id.img_left);
        img_right = findViewById(R.id.img_right);

        // округление углов на картинках - начало
        img_left.setClipToOutline(true);
        img_right.setClipToOutline(true);

        //развернуть игру на весь экран - начало
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //развернуть игру на весь экран - конец


        //вызов диалогового окна в начале игры - начало
        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);//hide a title
        dialog.setContentView(R.layout.previewdialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));//transparent background of dialog wnd
        dialog.setCancelable(false); //окно нельзя закрыть кропкой назад

        //кнопка, которая закрывает диалог окно - начало
        TextView btnclose = (TextView)dialog.findViewById(R.id.btnclose);
        btnclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //обработка нажатия кнопки - начало
                try {
                    Intent intent = new Intent(Level1.this,GameLevels.class);
                    startActivity(intent);
                    finish();
                }
                catch (Exception e){
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
                startLevel=true;
                levelTimer= new LevelTimer(tv_levelTime,getResources().getString(R.string.levelsucces), getResources().getString(R.string.levelendone),startLevel);
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
        TextView btnclose2 = (TextView)dialogEnd.findViewById(R.id.btnclose);
        btnclose2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //обработка нажатия кнопки - начало
                try {
                    Intent intent = new Intent(Level1.this,GameLevels.class);
                    startActivity(intent);
                    finish();
                }
                catch (Exception e){

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
                    Intent intent = new Intent(Level1.this,Level2.class);
                    startActivity(intent);
                    finish();
                }
                catch (Exception e) {

                }

                dialogEnd.dismiss();//close dialog wnd
            }
            //обработка нажатия кнопки - конец
        });
        //кнопка, которая переходит к уровню - конец
        //___________________________________



        // bnt back start
        Button btn_back = (Button)findViewById(R.id.btn_lvl_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Level1.this,GameLevels.class);
                    startActivity(intent);
                    finish();
                }
                catch (Exception e){
                }
            }
        });
        //btn back end




        numLeft = random.nextInt(10);//любое число 0..9
        img_left.setImageResource(array.images1[numLeft]);//set image


        numRight = random.nextInt(10);//любое число 0..9
        //Цикл, проверяющий равенство чисел
        while (numLeft == numRight)
        {
            numRight = random.nextInt(10);
        }

        img_right.setImageResource(array.images1[numRight]);


        //нажатие на left картинку start
        img_left.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //условие касания картинки - начало
                if (event.getAction() == MotionEvent.ACTION_DOWN)
                {
                    img_right.setEnabled(false);
                    if(numLeft>numRight)img_left.setImageResource(R.drawable.img_true);
                    else img_left.setImageResource(R.drawable.img_false);

                    if (timerStarted == true) {
                        roundTimer.cancel();
                        timerCancel=true;
                        timerStarted=false;
                        timerCancel=false;
                    }
                }
                else if (event.getAction() == MotionEvent.ACTION_UP)
                {

                    // SET TIMER
                    roundTimer = new RoundTimer(millisInFuture, countDownInterval, pb_timeLeft,timerCancel);

                    roundTimer.start();
                    timerStarted=true;

                //__________________________________________________

                if(numLeft>numRight)
                    {
                        if(count<20) count=count+1;
                            //закрашиваем прогресс серым цветом
                        for (int i=0; i<20; i++) {
                            tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        //закрашиваем правильные ответы зеленым
                        for (int i=0; i<count; i++) {
                            tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }

                    }
                    else
                        {
                            if (count > 0)
                            {
                                if (count == 1) count = 0;
                                else count = count - 2;
                            }

                            for (int i=0; i<=19; i++) {
                                tv = findViewById(progress[i]);
                                tv.setBackgroundResource(R.drawable.style_points);
                            }
                            //закрашиваем правильные ответы зеленым
                            for (int i=0; i<count; i++) {
                                tv = findViewById(progress[i]);
                                tv.setBackgroundResource(R.drawable.style_points_green);
                            }
                        }
                    if (count == 20)
                        {
                            tvdend.setText(levelTimer.runTimer());
                            dbEditor = new DataBaseEditor(1,1,"Madx", "Levsdfsdfsdfel1", levelTimer.getSeconds(), context);
                            startLevel=false;
                            levelTimer.resetSeconds();
                            dialogEnd.show();
                    }
                    else
                    {
                        numLeft = random.nextInt(10);//любое число 0..9
                        img_left.setImageResource(array.images1[numLeft]);//set image
                        img_left.startAnimation(a);


                        numRight = random.nextInt(10);//любое число 0..9
                        //Цикл, проверяющий равенство чисел
                        while (numLeft == numRight)
                        {
                            numRight = random.nextInt(10);
                        }

                        img_right.setImageResource(array.images1[numRight]);
                        img_right.startAnimation(a);
                        img_right.setEnabled(true);
                    }
                }
                //условие касания картинки - end
                return true;
            }
        });
        //нажатие на left картинку end

        //нажатие на right картинку start
        img_right.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //условие касания картинки - начало
                if (event.getAction() == MotionEvent.ACTION_DOWN)
                {
                    img_left.setEnabled(false);
                    if(numLeft<numRight)img_right.setImageResource(R.drawable.img_true);
                    else img_right.setImageResource(R.drawable.img_false);

                    if (timerStarted == true) {
                        roundTimer.cancel();
                        timerCancel=true;
                        timerStarted=false;
                        timerCancel=false;
                    }
                }
                else if (event.getAction() == MotionEvent.ACTION_UP)
                {
                    // TIMER Start
                    roundTimer = new RoundTimer(millisInFuture, countDownInterval, pb_timeLeft,timerCancel);
                    roundTimer.start();
                    timerStarted=true;

                    //__________________


                    if(numRight>numLeft)
                    {
                        if(count<20) count=count+1;
                        //закрашиваем прогресс серым цветом
                        for (int i=0; i<20; i++) {
                            tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        //закрашиваем правильные ответы зеленым
                        for (int i=0; i<count; i++) {
                            tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }

                    }
                    else
                    {
                        if (count > 0)
                        {
                            if (count == 1) count = 0;
                            else count = count - 2;
                        }

                        for (int i=0; i<=19; i++) {
                            tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        //закрашиваем правильные ответы зеленым
                        for (int i=0; i<count; i++) {
                            tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                    }
                    if (count == 20)
                    {
                        tvdend.setText(levelTimer.runTimer());
                    //    init();
                        dbEditor = new DataBaseEditor(1,1,"Madx", "Levsdfsdfsdfel1", levelTimer.getSeconds(), context);
                        startLevel=false;
                        levelTimer.resetSeconds();
                        dialogEnd.show();
                    }
                    else
                    {
                        numLeft = random.nextInt(10);//любое число 0..9
                        img_left.setImageResource(array.images1[numLeft]);//set image
                        img_left.startAnimation(a);


                        numRight = random.nextInt(10);//любое число 0..9
                        //Цикл, проверяющий равенство чисел
                        while (numLeft == numRight)
                        {
                            numRight = random.nextInt(10);
                        }

                        img_right.setImageResource(array.images1[numRight]);
                        img_right.startAnimation(a);
                        img_left.setEnabled(true);
                    }
                }
                //условие касания картинки - end
                return true;
            }
        });
        //нажатие на right картинку end


    }


    class RoundTimer extends CountDownTimer {
        final ProgressBar pb;

        final int[] progress = {
                R.id.point1,R.id.point2, R.id.point3, R.id.point4, R.id.point5, R.id.point6, R.id.point7,
                R.id.point8, R.id.point9,R.id.point10,R.id.point11,R.id.point12,R.id.point13,R.id.point14,R.id.point15,
                R.id.point16,R.id.point17,R.id.point18,R.id.point19,R.id.point20,};
        final Animation a = AnimationUtils.loadAnimation(Level1.this, R.anim.alpha);

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
            timerFinish=false;
            pb.setProgress((int) millisUntilFinished / progressScale);
            if (millisUntilFinished < 2000) {
                pb.setProgressTintList(ColorStateList.valueOf(Color.RED));
            } else
                pb.setProgressTintList(ColorStateList.valueOf(Color.GREEN));
        }

        @Override
        public void onFinish() {

            count=count-2;
            for (int i = 0; i <= 19; i++) {
                tv = findViewById(progress[i]);
                tv.setBackgroundResource(R.drawable.style_points);
            }
            //закрашиваем правильные ответы зеленым
            for (int i = 0; i < count; i++) {
                tv = findViewById(progress[i]);
                tv.setBackgroundResource(R.drawable.style_points_green);
            }

            numLeft = random.nextInt(10);//любое число 0..9
            img_left.setImageResource(array.images1[numLeft]);//set image
            img_left.startAnimation(a);


            numRight = random.nextInt(10);//любое число 0..9
            //Цикл, проверяющий равенство чисел
            while (numLeft == numRight) {
                numRight = random.nextInt(10);
            }

            img_right.setImageResource(array.images1[numRight]);
            img_right.startAnimation(a);

            roundTimer = new RoundTimer(millisInFuture, countDownInterval, pb_timeLeft,timerCancel);
            roundTimer.start();
            timerStarted=true;

        }


    }



    //system btn BACK Start
    @Override
    public void onBackPressed()
    {

        if (backPressedTime + 2000 > System.currentTimeMillis())
        {
            try
            {
                backToast.cancel();
                Intent intent = new Intent(Level1.this,GameLevels.class);
                startActivity(intent);finish();
            }
            catch (Exception e)
            {

            }
        }
        else
        {
            backToast = Toast.makeText(getBaseContext(),"Нажмите ещё раз, чтобы выйти из уровня",Toast.LENGTH_LONG);
            backToast.show();
        }
        backPressedTime = System.currentTimeMillis();
    }
    //system btn BACK End


}


