package space.korolev.quiz;

import java.util.Random;

public class Number {
    public int imageArabic;
    public int imageRome;
    public int power;
    private Random r;
    private boolean useRome = false;

    Number(int imgArabic, int pwr, int ingRome){
        imageArabic=imgArabic;
         imageRome=ingRome;
        power=pwr;
    }

    int GetImage(){
        useRome= r.nextInt(20) > 10;
        if (useRome) return imageRome;
        else return imageArabic;
    }

}
