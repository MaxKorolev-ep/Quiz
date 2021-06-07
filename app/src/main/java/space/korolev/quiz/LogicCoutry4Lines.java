package space.korolev.quiz;

import android.util.Log;
import android.widget.TextView;

import java.util.Random;

public class LogicCoutry4Lines {
    public Random random = new Random();
    public String strResult;
    int randNum;
    public String[] arrResult = {"", "", "", "", ""};
    public String[] arrText = {"", "", "", "", "", ""};
    String[] arrCountry;

    public LogicCoutry4Lines(TextView question, TextView var1, TextView var2, TextView var3, TextView var4, String[] countryArr) {
        arrCountry = countryArr;
        randomCountry();
        var1.setText(arrText[0]);
        var2.setText(arrText[1]);
        var3.setText(arrText[2]);
        var4.setText(arrText[3]);
        question.setText(arrText[4]);
        strResult = arrText[5];
    }

    public String[] randomCountry() {
        randNum = random.nextInt(arrCountry.length) + 2;
        while (randNum % 2 == 1) randNum = random.nextInt(arrCountry.length) + 2;
        randNum = randNum - 2;
        arrResult[4] = arrCountry[randNum];   //country
        arrResult[0] = arrCountry[randNum + 1]; //captain city

        randNum = random.nextInt(arrCountry.length) + 2;
        while (randNum % 2 == 0 || arrResult[0] == arrCountry[randNum - 2])
            randNum = random.nextInt(arrCountry.length) + 2;
        arrResult[1] = arrCountry[randNum - 2]; //captain city var1
        Log.d("ResArr", "var1 " + randNum);

        randNum = random.nextInt(arrCountry.length) + 2;
        while (randNum % 2 == 0 || arrResult[0] == arrCountry[randNum - 2] || arrResult[1] == arrCountry[randNum - 2])
            randNum = random.nextInt(arrCountry.length) + 2;
        arrResult[2] = arrCountry[randNum - 2]; //captain city var2
        Log.d("ResArr", "var2 " + randNum);

        randNum = random.nextInt(arrCountry.length) + 2;
        while (randNum % 2 == 0 || arrResult[0] == arrCountry[randNum - 2] || arrResult[1] == arrCountry[randNum - 2] || arrResult[2] == arrCountry[randNum - 2])
            randNum = random.nextInt(arrCountry.length) + 2;
        arrResult[3] = arrCountry[randNum - 2]; //captain city var3
        Log.d("ResArr", "var3 " + randNum);
        Log.d("ResArr", "________-------_________ ");

        int i;
        i = random.nextInt(4);
        arrText[i] = arrResult[0];
        arrText[5] = arrResult[0];
        i = random.nextInt(4);
        while (arrText[i] == arrResult[0]) i = random.nextInt(4);
        arrText[i] = arrResult[1];
        i = random.nextInt(4);
        while (arrText[i] == arrResult[0] || arrText[i] == arrResult[1]) i = random.nextInt(4);
        arrText[i] = arrResult[2];

        i = random.nextInt(4);
        while (arrText[i] == arrResult[0] || arrText[i] == arrResult[1] || arrText[i] == arrResult[2])
            i = random.nextInt(4);
        arrText[i] = arrResult[3];

        arrText[4] = arrResult[4];//question

        return arrText;
    }


}

