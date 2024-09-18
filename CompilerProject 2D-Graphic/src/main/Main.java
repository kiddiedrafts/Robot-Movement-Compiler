package main;

import java.io.*;
import java.util.*;

import parser.Parser;

public class Main {

    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);

        int Lx, Ux, Ly, Uy, Ox, Oy, N;

        int[][] array = null;

        String str;
        String[] temp;

        str = scanner.nextLine();
        temp = str.split(": ");
        Lx = Integer.parseInt(temp[1]);

        str = scanner.nextLine();
        temp = str.split(": ");
        Ux = Integer.parseInt(temp[1]);

        if (Lx > Ux)
            throw new Error("Error in Ux");


        str = scanner.nextLine();
        temp = str.split(": ");
        Ly = Integer.parseInt(temp[1]);

        str = scanner.nextLine();
        temp = str.split(": ");
        Uy = Integer.parseInt(temp[1]);

        if (Lx > Ux)
            throw new Error("Error in Uy");


        str = scanner.nextLine();
        temp = str.split(": ");
        Ox = Integer.parseInt(temp[1]);

        if (Ox > Ux || Ox < Lx)
            throw new Error("Error in Ox");


        str = scanner.nextLine();
        temp = str.split(": ");
        Oy = Integer.parseInt(temp[1]);

        if (Oy > Uy || Oy < Ly)
            throw new Error("Error in Oy");

        str = scanner.nextLine();
        temp = str.split(": ");
        N = Integer.parseInt(temp[1]);

        int t;
        array = new int[N][2];
        for (int j = 0; j < N; j++) {

            t = scanner.nextInt();
            if (t > Ux || t < Lx)
                throw new Error("Error in barrier (x)");
            array[j][0] = t;

            t = scanner.nextInt();
            if (t > Uy || t < Ly)
                throw new Error("Error in barrier (y)");
            array[j][1] = t;
        }

        List<Integer> list = new ArrayList<Integer>();
        int ch;

        while (true) {

            ch = (char)System.in.read();
            list.add(ch);

            if (ch == '/') {
                ch = (char)System.in.read();
                list.add(ch);
                if (ch == '/') {
                    do {
                        ch = (char)System.in.read();
                        list.add(ch);
                    } while (ch != '\n');
                }
            }

            else if (ch == '{') {
                do {
                    ch = (char)System.in.read();
                    list.add(ch);
                } while (ch != '}');
            }

            else if (ch == 'e' || ch == 'E') {
                ch = (char)System.in.read();
                list.add(ch);
                if (ch == 'n' || ch == 'N') {
                    ch = (char)System.in.read();
                    list.add(ch);
                    if (ch == 'd' || ch == 'D') {
                        list.add((int)'#');
                        break;
                    }
                }
            }
        }

        //System.out.println(list);

        Parser p = new Parser(Lx, Ux, Ly, Uy, Ox, Oy, N, array, list);
        p.seq();

    }
}
