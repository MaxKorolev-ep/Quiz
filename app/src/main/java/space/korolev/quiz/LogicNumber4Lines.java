package space.korolev.quiz;

import android.util.Log;
import android.widget.TextView;

import java.util.Random;

public class LogicNumber4Lines {
    public Random random = new Random();
    String strResult,strLine, strMiddleLine;
  public   String[] arrLine = {"",""};
    String[] arrText = {"","","","",""};
    String[] arrResult = {"","","",""};
    int firstX, secondX, thirdX, result, operation, resultmidlle;
    char[]  mathOp = {'*','/','+','-'};


    public LogicNumber4Lines(TextView question, TextView var1, TextView var2, TextView var3, TextView var4, int n) {
        mathExpression (n);
        arrFunc(n);
        var1.setText(arrText[0]);
        var2.setText(arrText[1]);
        var3.setText(arrText[2]);
        var4.setText(arrText[3]);
        question.setText(arrText[4]);

    }


    public String[]  mathExpression (int num)
    {
        firstX = random.nextInt(20)-10;
        secondX = random.nextInt(20)-10;
        thirdX = random.nextInt(7)-3;

        //initialize random math operation
        char op1 = mathOper();
        char op2 = mathOper();

        //choose difficult
        if (num == 1)
        {
           // Log.d("NumOper", "Num is: " +num);
            try {
                switch (op1) {
                    case '+':
                        result = firstX + secondX;
                        strResult = Integer.toString(result);
                    break;
                    case '-':
                        result = firstX - secondX;
                        strResult = Integer.toString(result);
                    break;
                    case '*':
                        result = firstX * secondX;
                        strResult = Integer.toString(result);
                    break;
                    case '/':
                        if (secondX != 0) {
                            result = firstX / secondX;
                            strResult = Integer.toString(result);
                        } else strResult = "Error";
                    break;
                }

                //creating of final string
                arrLine[0] = strResult;
                if (secondX<0 )strLine=Integer.toString(firstX)+" "+op1+" " + "("+Integer.toString(secondX)+ ")" + " =";
                else strLine=Integer.toString(firstX)+" "+op1+" "+Integer.toString(secondX) + " =";
                arrLine[1] = strLine;
                //end

            } catch (NumberFormatException nfe) {
                Log.d("NumOper", "Cant be");
            }
        }
        else Log.d("NumOper", "Erreeeerwerwor");

        if (num==2)
    {
        // Log.d("NumOper", "Num is: " +num);
        try {
            switch (op1) {
                case '+':
                    resultmidlle = firstX + secondX;
                    break;
                case '-':
                    resultmidlle = firstX - secondX;
                    break;
                case '*':
                    resultmidlle = firstX * secondX;
                    break;
                case '/':
                    while (secondX==0) secondX = random.nextInt(20)-10;
                    if (secondX != 0) {
                        resultmidlle = firstX / secondX;
                    }
                    break;
            }

            // creating of middle string result
            if (secondX<0 )strMiddleLine=Integer.toString(firstX)+" "+op1+" " + "("+Integer.toString(secondX)+ ")";
            else strMiddleLine=Integer.toString(firstX)+" "+op1+" "+Integer.toString(secondX);
            //end

            switch (op2) {
                case '+':
                    result = resultmidlle + thirdX;
                    strResult = Integer.toString(result);
                    break;
                case '-':
                    result = resultmidlle - thirdX;
                    strResult = Integer.toString(result);
                    break;
                case '*':
                    result = resultmidlle * thirdX;
                    strResult = Integer.toString(result);
                    break;
                case '/':

                    if (thirdX != 0) {
                        result = resultmidlle / thirdX;
                        strResult = Integer.toString(result);
                    } else strResult = "Error";
                    break;
            }

            //creating of final string
            arrLine[0] = strResult;
            if (thirdX<0 )strLine= "(" + strMiddleLine + ")" +" "+op2+" " + "("+Integer.toString(thirdX)+ ")" + " =";
            else strLine= "(" + strMiddleLine + ")" + " "+op2+" "+Integer.toString(thirdX) + " =";
            arrLine[1] = strLine;
            //end

        } catch (NumberFormatException nfe) {
            Log.d("NumOper", "Cant be");
        }
    }
    else Log.d("NumOper", "Erreeeerwerwor");

    return arrLine;
    }

    //making variants of answering (string)
    String[] arrFunc(int num)
    {
        arrResult[0] = arrLine[0];
        if (num==1) { // 9*9 = 81, 81*2+1=163
            arrResult[1] = Integer.toString(random.nextInt(163) - 81);
            while (arrResult[1] == arrResult[0])
                arrResult[1] = Integer.toString(random.nextInt(163) - 81);

            arrResult[2] = Integer.toString(random.nextInt(163) - 81);
            while ((arrResult[1] == arrResult[2]) || (arrResult[0] == arrResult[2]))
                arrResult[2] = Integer.toString(random.nextInt(163) - 81);

            arrResult[3] = Integer.toString(random.nextInt(163) - 81);
            while ((arrResult[1] == arrResult[3]) || (arrResult[0] == arrResult[3]) || (arrResult[0] == arrResult[3]))
                arrResult[3] = Integer.toString(random.nextInt(163) - 81);
        }

        if (num==2) { // 9*9*3 = 243, 243*2+1=487
            arrResult[1] = Integer.toString(random.nextInt(487) - 243);
            while (arrResult[1] == arrResult[0])
                arrResult[1] = Integer.toString(random.nextInt(487) - 243);

            arrResult[2] = Integer.toString(random.nextInt(487) - 243);
            while ((arrResult[1] == arrResult[2]) || (arrResult[0] == arrResult[2]))
                arrResult[2] = Integer.toString(random.nextInt(487) - 243);

            arrResult[3] = Integer.toString(random.nextInt(487) - 243);
            while ((arrResult[1] == arrResult[3]) || (arrResult[0] == arrResult[3]) || (arrResult[0] == arrResult[3]))
                arrResult[3] = Integer.toString(random.nextInt(487) - 243);
        }


        int i;
        i = random.nextInt(4);
        arrText[i] = arrResult[0];

        i = random.nextInt(4);
        while (arrText[i] == arrResult[0])i = random.nextInt(4);
        arrText[i] = arrResult[1];

        i = random.nextInt(4);
        while (arrText[i] == arrResult[0] || arrText[i] == arrResult[1])i = random.nextInt(4);
        arrText[i] = arrResult[2];

        i = random.nextInt(4);
        while (arrText[i] == arrResult[0] || arrText[i] == arrResult[1]|| arrText[i] == arrResult[2])i = random.nextInt(4);
        arrText[i] = arrResult[3];

        arrText[4] = arrLine[1];
        return arrText;
    }




    public char mathOper()
    {
        operation = random.nextInt(4);
        return mathOp[operation];
    }


}
