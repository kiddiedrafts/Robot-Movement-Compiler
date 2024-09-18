package parser;

import java.io.*;
import java.util.*;
import lexer.Lexer;
import lexer.Tag;

public class Parser {

    public static int lookahead;
    public int Lx, Ux, Ly, Uy, Lz, Uz, Ox, Oy, Oz, N;
    public int x, y, z;
    public int[][] array;
    public List<Integer> list;
    public int errNum = -1;
    public int distance = 0;
    public double displacement = 0;

    public static int counter = 0;

    public Parser(int Lx, int Ux, int Ly, int Uy, int Lz, int Uz, int Ox, int Oy, int Oz, int N, int arr[][], List<Integer> li) throws IOException {
        this.Lx = Lx;
        this.Ux = Ux;
        this.Ly = Ly;
        this.Uy = Uy;
        this.Lz = Lz;
        this.Uz = Uz;
        this.Ox = Ox;
        this.Oy = Oy;
        this.Oz = Oz;
        this.N = N;
        array = new int[N][3];
        for (int i = 0; i < N; i++) {
            this.array[i][0] = arr[i][0];
            this.array[i][1] = arr[i][1];
            this.array[i][2] = arr[i][2];
        }
        list = new ArrayList<Integer>();
        this.list = li;
        Lexer l = new Lexer(this.list);
        lookahead = l.scan(counter).tag;
    }



    void match(int t) throws IOException {
        if(lookahead == t) {
            Lexer l = new Lexer(this.list);
            lookahead = l.scan(l.getCounter()).tag;
        } else throw new Error("Error in match");
    }

    public void seq() throws IOException {
        if(lookahead == Tag.BEGIN) {
            errNum++;
            this.x = this.Ox;
            this.y = this.Oy;
            this.z = this.Oz;
            System.out.print((char)'(');
            System.out.print(this.Ox);
            System.out.print((char)',');
            System.out.print(this.Oy);
            System.out.print((char)',');
            System.out.print(this.Oz);
            System.out.print((char)')');
            System.out.println();
            match(Tag.BEGIN);
        }
        moves();
        if(lookahead == Tag.END) {
            System.out.print("L = ");
            System.out.println(getDistance());
            System.out.print("D = ");
            System.out.println(getDisplacement());
            return;
        }
        return;
    }

    public void moves() throws IOException {

        while(true) {

             if(lookahead == Tag.NORTH) {
                boolean flag = true;
                errNum++;
                this.y += 1;
                for (int i = 0; i < this.N; i++) {
                    if(this.x == this.array[i][0] && this.y == this.array[i][1] && this.z == this.array[i][2]) {
                        this.y -= 1;
                        System.out.println("Error in instr " + errNum);
                        match(Tag.NORTH);
                        flag = false;
                    }
                }
                if(this.y > this.Uy) {
                    this.y -= 1;
                    System.out.println("Error in instr " + errNum);
                    match(Tag.NORTH);
                    flag = false;
                }
                if (flag == true) {
                    this.distance++;
                    System.out.print((char)'(');
                    System.out.print(this.x);
                    System.out.print((char)',');
                    System.out.print(this.y);
                    System.out.print((char)',');
                    System.out.print(this.z);
                    System.out.print((char)')');
                    System.out.println();
                    match(Tag.NORTH);
                }
            }
            else if(lookahead == Tag.SOUTH) {
                boolean flag = true;
                errNum++;
                this.y -= 1;
                for (int i = 0; i < this.N; i++) {
                    if(this.x == this.array[i][0] && this.y == this.array[i][1] && this.z == this.array[i][2]) {
                        this.y += 1;
                        System.out.println("Error in instr " + errNum);
                        match(Tag.SOUTH);
                        flag = false;
                    }
                }
                if (this.y < this.Ly) {
                    this.y += 1;
                    System.out.println("Error in instr " + errNum);
                    match(Tag.SOUTH);
                    flag = false;
                }
                if (flag == true){
                    this.distance++;
                    System.out.print((char)'(');
                    System.out.print(this.x);
                    System.out.print((char)',');
                    System.out.print(this.y);
                    System.out.print((char)',');
                    System.out.print(this.z);
                    System.out.print((char)')');
                    System.out.println();
                    match(Tag.SOUTH);
                }
            }

            else if(lookahead == Tag.EAST) {
                boolean flag = true;
                errNum++;
                this.x += 1;

                for (int i = 0; i < this.N; i++) {
                    if(this.x == this.array[i][0] && this.y == this.array[i][1] && this.z == this.array[i][2]) {
                        this.x -= 1;
                        System.out.println("Error in instr " + errNum);
                        match(Tag.EAST);
                        flag = false;
                    }
                }
                if(this.x > this.Ux) {
                    this.x -= 1;
                    System.out.println("Error in instr " + errNum);
                    match(Tag.EAST);
                    flag = false;
                }
                if (flag  == true){
                    this.distance++;
                    System.out.print((char)'(');
                    System.out.print(this.x);
                    System.out.print((char)',');
                    System.out.print(this.y);
                    System.out.print((char)',');
                    System.out.print(this.z);
                    System.out.print((char)')');
                    System.out.println();
                    match(Tag.EAST);
                }
            }
            else if(lookahead == Tag.WEST) {
                boolean flag = true;
                errNum++;
                this.x -= 1;

                for (int i = 0; i < this.N; i++) {
                    if(this.x == this.array[i][0] && this.y == this.array[i][1] && this.z == this.array[i][2]) {
                        this.x += 1;
                        System.out.println("Error in instr " + errNum);
                        match(Tag.WEST);
                        flag = false;
                    }
                }

                if(this.x < this.Lx) {
                    this.x += 1;
                    System.out.println("Error in instr " + errNum);
                    match(Tag.WEST);
                    flag = false;
                }
                if (flag == true) {
                    this.distance++;
                    System.out.print((char)'(');
                    System.out.print(this.x);
                    System.out.print((char)',');
                    System.out.print(this.y);
                    System.out.print((char)',');
                    System.out.print(this.z);
                    System.out.print((char)')');
                    System.out.println();
                    match(Tag.WEST);
                }
            }

            else if(lookahead == Tag.UP) {
                boolean flag = true;
                errNum++;
                this.z += 1;

                for (int i = 0; i < this.N; i++) {
                    if(this.x == this.array[i][0] && this.y == this.array[i][1] && this.z == this.array[i][2]) {
                        this.z -= 1;
                        System.out.println("Error in instr " + errNum);
                        match(Tag.UP);
                        flag = false;
                    }
                }

                if(this.z > this.Uz) {
                    this.z -= 1;
                    System.out.println("Error in instr " + errNum);
                    match(Tag.UP);
                    flag = false;
                }
                if (flag == true) {
                    this.distance++;
                    System.out.print((char)'(');
                    System.out.print(this.x);
                    System.out.print((char)',');
                    System.out.print(this.y);
                    System.out.print((char)',');
                    System.out.print(this.z);
                    System.out.print((char)')');
                    System.out.println();
                    match(Tag.UP);
                }
            }

            else if(lookahead == Tag.DOWN) {
                boolean flag = true;
                errNum++;
                this.z -= 1;

                for (int i = 0; i < this.N; i++) {
                    if(this.x == this.array[i][0] && this.y == this.array[i][1] && this.z == this.array[i][2]) {
                        this.z += 1;
                        System.out.println("Error in instr " + errNum);
                        match(Tag.DOWN);
                        flag = false;
                    }
                }

                if(this.z < this.Lz) {
                    this.z += 1;
                    System.out.println("Error in instr " + errNum);
                    match(Tag.DOWN);
                    flag = false;
                }
                if (flag == true) {
                    this.distance++;
                    System.out.print((char)'(');
                    System.out.print(this.x);
                    System.out.print((char)',');
                    System.out.print(this.y);
                    System.out.print((char)',');
                    System.out.print(this.z);
                    System.out.print((char)')');
                    System.out.println();
                    match(Tag.DOWN);
                }
            } else
                return;
        }
    }

    public int getDistance() {
        return this.distance;
    }

    public double getDisplacement() {
        this.displacement = Math.sqrt(Math.pow((this.x - this.Ox), 2) + Math.pow((this.y - this.Oy), 2) + Math.pow((this.z - this.Oz), 2));
        return this.displacement;
    }
}
