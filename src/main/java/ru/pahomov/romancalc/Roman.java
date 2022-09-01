package ru.pahomov.romancalc;

enum Roman
{
    I("I", 1),
    IV("IV", 4),
    V("V", 5),
    IX("IX", 9),
    X("X", 10),
    XL("XL", 40),
    L("L", 50),
    XC("XC", 90),
    C("C", 100);

    private final String romanStr;
    private final int romanValue;

    Roman(String romanStr, int romanValue)
    { this.romanStr = romanStr; this.romanValue = romanValue; }

    // --- GET
    public String getRomanStr()
    { return romanStr; }
    public int getRomanValue()
    { return romanValue; }
}
