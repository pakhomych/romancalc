package ru.pahomov.romancalc;

public enum Operator
{
    ADDITION('+', 0),
    SUBTRACTION('-', 1),
    DIVISION('/', 2),
    MULTIPLICATION('*', 3);

    private final char operatorChar;
    private final int operatorIndex;
    
    Operator(char operatorChar, int operatorIndex)
    { this.operatorChar = operatorChar; this.operatorIndex = operatorIndex; }
    
    // --- GET
    public char getOperatorChar()
    { return operatorChar; }
    public int getOperatorIndex()
    { return operatorIndex; }

    public static String getSplitExp()
    {
        String expStr = "";

        for(Operator operator : Operator.values())
        {
            expStr += String.format("((?!^)(?<=\\%1$c)|(?=\\%1$c))", operator.getOperatorChar());
            if(operator.getOperatorIndex() != Operator.values().length-1)
            { expStr += "|"; }
        }

        return expStr;
    }
}
