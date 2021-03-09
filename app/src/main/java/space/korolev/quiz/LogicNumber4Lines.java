package space.korolev.quiz;

import android.util.Log;
import android.widget.TextView;

import java.util.Random;

public class LogicNumber4Lines {
    public Random random = new Random();
    String strResult,strLine;
  public   String[] arrLine = {"",""};
    String[] arrText = {"","","","",""};
    String[] arrResult = {"","","",""};
    int firstX, secondX, thirdX, result, operation;
    char[]  mathOp = {'*','/','+','-'};


    LogicNumber4Lines(TextView question,TextView var1,TextView var2,TextView var3,TextView var4, int n) {
        mathExpression (n);
        arrFunc();
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
        thirdX = random.nextInt(20)-10;


        char op1 = mathOper();
        //  char op2 = mathOper();

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
                arrLine[0] = strResult;
                if (secondX<0 )strLine=Integer.toString(firstX)+" "+op1+" " + "("+Integer.toString(secondX)+ ")" + " =";
                    else strLine=Integer.toString(firstX)+" "+op1+" "+Integer.toString(secondX) + " =";
                arrLine[1] = strLine;

            } catch (NumberFormatException nfe) {
                Log.d("NumOper", "Cant be");
            }
        }
        else Log.d("NumOper", "Erreeeerwerwor");
/*
        else if (num==2)
        {
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
                        }
                        else strResult = "Error";
                        break;
                }

                switch (op2) {
                    case '+':
                        result = result + thirdX;
                        strResult =strResult + Integer.toString(result);
                        break;
                    case '-':
                        result = result - thirdX;
                        strResult =strResult + Integer.toString(result);
                        break;
                    case '*':
                        result = result * thirdX;
                        strResult =strResult + Integer.toString(result);
                        break;
                    case '/':
                        if (secondX != 0) {
                            result = result / thirdX;
                            strResult =strResult + Integer.toString(result);
                        }
                        else strResult = "Error";
                        break;
                }



                Log.d("NumOper", "Result is: " + result);

                Log.d("NumOper", "STRResult is: " + strResult);
            } catch (NumberFormatException nfe) {
                Log.d("NumOper", "Error");
            }
        }
*/

        return arrLine;
    }

    String[] arrFunc()
    {
        arrResult[0] = arrLine[0];

        arrResult[1] = Integer.toString(random.nextInt(163)-81);
        while (arrResult[1] == arrResult[0])
            arrResult[1]=Integer.toString(random.nextInt(163)-81);

        arrResult[2] = Integer.toString(random.nextInt(163)-81);
        while ((arrResult[1] == arrResult[2]) || (arrResult[0] == arrResult[2]))
            arrResult[2]=Integer.toString(random.nextInt(163)-81);

        arrResult[3] = Integer.toString(random.nextInt(163)-81);
        while ((arrResult[1] == arrResult[3]) || (arrResult[0] == arrResult[3]) || (arrResult[0] == arrResult[3]))
            arrResult[3] =Integer.toString(random.nextInt(163)-81);

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
