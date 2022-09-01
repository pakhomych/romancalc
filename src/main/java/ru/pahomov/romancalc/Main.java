package ru.pahomov.romancalc;

import java.util.Scanner;
import java.util.Arrays;

import ru.pahomov.romancalc.Roman;
import ru.pahomov.romancalc.Operator;

class Main
{
    public static String calc(String input) throws ExpressionException
    {
        String[] strArr = input.split(Operator.getSplitExp());
        
        String a    = strArr[0];
        String b    = strArr[2];
        char c      = strArr[1].charAt(0);

        
        if(!Converter.isRoman(a))
        {
            switch(c)
            {
                case '+': {
                              int arg1 = Integer.parseInt(a);
                              int arg2 = Integer.parseInt(b);

                              return Integer.toString(arg1 + arg2);
                          }
                case '-': {
                              int arg1 = Integer.parseInt(a);
                              int arg2 = Integer.parseInt(b);

                              return Integer.toString(arg1 - arg2);
                          }
                case '/': {
                              int arg1 = Integer.parseInt(a);
                              int arg2 = Integer.parseInt(b);

                              return Integer.toString(arg1 / arg2);
                          }
                case '*': {
                              int arg1 = Integer.parseInt(a);
                              int arg2 = Integer.parseInt(b);

                              return Integer.toString(arg1 * arg2);
                          }
            }
        }
        else
        {
            switch(c)
            {
                case '+': {
                              int arg1 = Converter.toArabic(a);
                              int arg2 = Converter.toArabic(b);

                              return Converter.toRoman(arg1 + arg2);
                          }
                case '-': {
                              int arg1 = Converter.toArabic(a);
                              int arg2 = Converter.toArabic(b);
                              int result = (arg1 - arg2);

                              if(result > 0)
                              { return Converter.toRoman(result); }
                              else { throw new ExpressionException("ERROR: Roman digits should be positive."); }
                          }
                case '/': {
                              int arg1 = Converter.toArabic(a);
                              int arg2 = Converter.toArabic(b);
                              int result = (arg1 / arg2);

                              if(result > 0)
                              { return Converter.toRoman(result); }
                              else { throw new ExpressionException("ERROR: Roman digits should be positive."); }
                          }
                case '*': {
                              int arg1 = Converter.toArabic(a);
                              int arg2 = Converter.toArabic(b);

                              return Converter.toRoman(arg1 * arg2);
                          }
            }
        }

        return null;
    }

    public static void main(String[] args) throws ExpressionException
    {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите выражение: ");
        String str = Converter.eraseSpacing(scanner.nextLine());

        if(Converter.isValid(str))
        { System.out.println("Результат: " + calc(str)); }
    }
}
