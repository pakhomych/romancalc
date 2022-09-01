package ru.pahomov.romancalc;

import ru.pahomov.romancalc.Roman;

class Converter
{
    private static int getCloserValue(int digit)
    {
        int closeMin    = 0;

        for(Roman value : Roman.values())
        {
            int cur = 0;

            if(value.getRomanValue() <= digit)
            {
                cur = value.getRomanValue();

                if(closeMin < cur)
                { closeMin = cur; }
            }
        }

        return closeMin;
    }

    private static String getRomanByArabic(int digit)
    {
        for(Roman roman : Roman.values())
        {
            if(roman.getRomanValue() == digit)
            { return roman.getRomanStr(); }
        }

        return null;
    }

    public static String toRoman(int digit)
    {
        String romanDigit = "";
        
        while(digit > 0)
        {
            romanDigit += getRomanByArabic(getCloserValue(digit));
            digit -= getCloserValue(digit);
        }

        return romanDigit;
    }

    public static int toArabic(String str)
    {
        char[] charArr = str.toCharArray();
        int arabicDigit = 0;

        for(int i = 0; i < charArr.length; i++)
        {
            for(Roman roman : Roman.values())
            {
                if(Character.toString(charArr[i]).equals(roman.getRomanStr()))
                { arabicDigit += roman.getRomanValue(); }
            }
        }

        return arabicDigit;
    }

    public static boolean isRoman(String str)
    { return str != null && toArabic(str) != 0; }
    
    public static boolean isArabic(String str)
    { return str != null && str.matches("(?<![.])\\b[0-9]+\\b(?!\\.[0-9])"); }

    public static boolean isOperator(char ch)
    {
        for(Operator operator : Operator.values())
        {
            if(operator.getOperatorChar() == ch)
            { return true; }
        }

        return false;
    }

    public static boolean isValid(String expStr) throws ExpressionException
    {
        String[] expStrArr = expStr.split(Operator.getSplitExp());

        if(expStrArr.length == 1)
        { throw new ExpressionException("ERROR: There is not an expression."); }
        else if(expStrArr.length < 3)
        { throw new ExpressionException("ERROR: Wrong operator."); }
        else if(expStrArr.length > 4)
        { throw new ExpressionException("ERROR: Might be only one operator."); }

        String arg1 = expStrArr[0];
        String arg2 = expStrArr[2];
        String operator = expStrArr[1];
        
        if(
                (!isRoman(arg1) && !isArabic(arg1)) ||
                (!isRoman(arg2) && !isArabic(arg2))
            )
        { throw new ExpressionException("ERROR: Illegal expression."); }

        if(
                (isRoman(arg1) && isArabic(arg2)) ||
                (isRoman(arg2) && isArabic(arg1))
            )
        { throw new ExpressionException("ERROR: Both format used at same time."); }

        if(
                isArabic(arg1) &&
                ((Integer.parseInt(arg1) <= 0 || Integer.parseInt(arg1) > 10) || 
                (Integer.parseInt(arg2) <= 0 || Integer.parseInt(arg2) > 10))
                
           )
        { System.out.println("ERROR: Arabic digits should't be less than 0 or greater than 10."); return false; }

        return true;
    }

    public static String eraseSpacing(String str)
    {
        String newStr = "";

        for(int i = 0; i < str.length(); i++)
        {
            if(str.charAt(i) != ' ' && str.charAt(i) != (char)9)
            { newStr += str.charAt(i); }
        }

        return newStr;
    }
}
