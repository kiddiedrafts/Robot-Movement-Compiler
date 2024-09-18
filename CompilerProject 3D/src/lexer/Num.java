package lexer;

import java.io.*;
import java.util.*;

public class Num extends Token {

    public static int value;

    public Num(int v) {
        super(Tag.NUM);
        value = v;
    }

    public static int getValue() {
        return value;
    }
}