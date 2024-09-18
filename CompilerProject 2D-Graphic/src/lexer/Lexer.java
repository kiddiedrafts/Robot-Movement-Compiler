package lexer;

import java.io.*;
import java.util.*;

import parser.Parser;

public class Lexer {

    public int line = 0;
    private int peek;
    public List<Integer> list;
    public Hashtable words = new Hashtable();

    void reserve(Word t) {
        words.put(t.lexeme, t);
    }

    public Lexer(List<Integer> l) {
        reserve(new Word(Tag.BEGIN, "begin"));
        reserve(new Word(Tag.NORTH, "north"));
        reserve(new Word(Tag.SOUTH, "south"));
        reserve(new Word(Tag.EAST, "east"));
        reserve(new Word(Tag.WEST, "west"));
        reserve(new Word(Tag.END, "end"));
        list = new ArrayList<Integer>();
        this.list = l;
    }

    public Token scan(int cntr) throws IOException {

        Parser.counter = cntr;

        for ( ; ; /*peek = list.get(counter)*/) {
            peek = list.get(Parser.counter);
            if (peek == ' ' || peek == '\t') {
                Parser.counter++;
                continue;
            }

            else if (peek == '/') {
                Parser.counter++;
                peek = list.get(Parser.counter);
                if (peek == '/') {
                    Parser.counter++;
                    while(peek != '\n') {
                        peek = list.get(Parser.counter);
                        Parser.counter++;
                    }
                }
            }

            else if(peek == '{') {
                Parser.counter++;
                peek = list.get(Parser.counter);
                Parser.counter++;
                while(peek != '}') {
                    peek = list.get(Parser.counter);
                    Parser.counter++;
                }
            }

            else if (peek == 13 || peek == 10) {
                if (peek == 13) {
                    Parser.counter++;
                    peek = list.get(Parser.counter);
                    if (peek == 10) {
                        Parser.counter++;
                        continue;
                    }
                } else if (peek == 10) {
                    Parser.counter++;
                    continue;
                }
            }

            else
                break;
        }

        if (Character.isDigit((char)peek)) {
            int v = 0;
            do {
                Parser.counter++;
                v = 10 * v + Character.digit(peek, 10);
                peek = list.get(Parser.counter);
            } while (Character.isDigit(peek));
            return new Num(v);
        }

        if (Character.isLetter((char)peek)) {
            StringBuffer b = new StringBuffer();
            do {
                Parser.counter++;
                b.append(Character.toLowerCase((char)peek));
                peek = list.get(Parser.counter);
                if (peek == '#')
                    break;
            } while (Character.isLetter((char)peek));

            String s = b.toString();
            Word w = (Word)words.get(s);
            if (w != null)
                return w;

            w = new Word(Tag.ID, s);
            words.put(s, w);
            return w;
        }

        Token t = new Token(peek);
        peek = ' ';
        return t;
    }

    public int getCounter() {
        return Parser.counter;
    }
}