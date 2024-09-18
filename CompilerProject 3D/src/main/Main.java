package main;

import java.io.*;
import java.util.*;

import parser.Parser;

public class Main {

    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);

        int Lx, Ux, Ly, Uy, Lz, Uz, Ox, Oy, Oz, N;

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
            throw new Error("Error in x boundaries");


        str = scanner.nextLine();
        temp = str.split(": ");
        Ly = Integer.parseInt(temp[1]);

        str = scanner.nextLine();
        temp = str.split(": ");
        Uy = Integer.parseInt(temp[1]);

        if (Lx > Ux)
            throw new Error("Error in y boundaries");

        str = scanner.nextLine();
        temp = str.split(": ");
        Lz = Integer.parseInt(temp[1]);

        str = scanner.nextLine();
        temp = str.split(": ");
        Uz = Integer.parseInt(temp[1]);

        if (Lz > Uz)
            throw new Error("Error in z boundaries");


        str = scanner.nextLine();
        temp = str.split(": ");
        Ox = Integer.parseInt(temp[1]);

        if (Ox > Ux || Ox < Lx)
            throw new Error("Error in Ox. Out of borders");


        str = scanner.nextLine();
        temp = str.split(": ");
        Oy = Integer.parseInt(temp[1]);

        if (Oy > Uy || Oy < Ly)
            throw new Error("Error in Oy. Out of borders");


        str = scanner.nextLine();
        temp = str.split(": ");
        Oz = Integer.parseInt(temp[1]);

        if (Oz > Uz || Oz < Lz)
            throw new Error("Error in Oz. Out of borders");


        str = scanner.nextLine();
        temp = str.split(": ");
        N = Integer.parseInt(temp[1]);

        int t;
        array = new int[N][3];
        for (int j = 0; j < N; j++) {

            t = scanner.nextInt();
            if (t > Ux || t < Lx)
                throw new Error("Error in barrier's x. out of borders");
            array[j][0] = t;

            t = scanner.nextInt();
            if (t > Uy || t < Ly)
                throw new Error("Error in barrier's y. out of borders");
            array[j][1] = t;

            t = scanner.nextInt();
            if (t > Uz || t < Lz)
                throw new Error("Error in barrier's z. out of borders");
            array[j][2] = t;
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

            if (ch == 'e' || ch == 'E') {
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

        Parser p = new Parser(Lx, Ux, Ly, Uy, Lz, Uz, Ox, Oy, Oz, N, array, list);
        p.seq();

    }
}
