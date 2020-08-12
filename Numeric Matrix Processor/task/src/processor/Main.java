package processor;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        boolean flag = false;
        while (true) {
            System.out.printf("1. Add matrices%n 2. Multiply matrix to a constant%n 3. Multiply matrices%n 4.Transpose matrix%n 5. Calculate a determinant%n 0. Exit%n");
            int choose = scanner.nextInt();
            switch (choose) {
                case 1: {
                    Add();
                    break;
                }
                case 2: {
                    MultiplyConst();
                    break;
                }
                case 3: {
                    Multiply();
                    break;
                }
                case 4: {
                    System.out.printf("1. Main diagonal%n 2. Side diagonal%n 3. Vertical line%n 4. Horizontal line%n");
                    int transpose = scanner.nextInt();
                    switch (transpose){
                        case 1:{
                            MainDiagonal();
                            break;
                        }
                        case 2:{
                            SideDiagonal();
                            break;
                        }
                        case 3:{
                            VerticalLine();
                            break;
                        }
                        case 4:{
                            HorizontalLine();
                            break;
                        }
                    }
                    break;
                }
                case 5: {
                    determinantMass();
                    break;
                }
                case 6: {
                    inverseMatrix();
                    break;
                }
                case 0:
                    flag=true;
                    break;
            }
            if (flag==true) break;
        }
    }

    private static void inverseMatrix() {
        System.out.println("Enter matrix size: ");
        int nA = scanner.nextInt();
        int mA = scanner.nextInt();
        double[][] A = new double[nA][mA];
        for (int i = 0; i < nA; i++) {
            for (int j = 0; j < mA; j++) {
                A[i][j] = scanner.nextDouble();
            }
        }
        double[][] temp = new double[nA][nA];
        double[][] time = new double[nA][nA];
        double det = determinant(A,nA);
        for (int f=0;f<nA;f++){
            for (int i =0; i<nA;i++){
                getMinor(A,temp,f,i,nA);
                time[f][i] =Math.pow(-1,f+i) * determinant(temp,nA-1);
            }
        }
        for (int i=0;i<nA;i++){
            for (int j=0; j<mA;j++){
                temp[j][i]=time[i][j];
            }
        }
        for (int i=0;i<nA;i++){
            System.out.println();
            for (int j=0; j<nA;j++)
                System.out.printf("%.2f ",(1/det) * temp[i][j]);
        }
        System.out.println();
    }

    private static void determinantMass() {
        System.out.println("Enter matrix size: ");
        int nA = scanner.nextInt();
        int mA = scanner.nextInt();
        double[][] A = new double[nA][mA];
        for (int i = 0; i < nA; i++) {
            for (int j = 0; j < mA; j++) {
                A[i][j] = scanner.nextDouble();
            }
        }
        System.out.println(determinant(A,nA));

    }

    private static double determinant(double[][] a, int n) {
        double D=0;
        if (n==1) return a[0][0];
        double[][] temp = new double[n][n];
        int sign =1;
        for (int f=0;f<n;f++){
            getMinor(a,temp,0,f,n);
            D+=sign*a[0][f]*determinant(temp,n-1);
            sign=-sign;
        }
        return D;
    }

    private static void getMinor(double[][] a, double[][] temp, int p, int q, int n) {
        int i=0,j=0;
        for (int row=0;row<n;row++){
            for (int col=0;col<n;col++){
                if (row !=p && col!=q){
                    temp[i][j++]=a[row][col];
                    if (j==n-1){
                        j=0;
                        i++;
                    }
                }
            }
        }
    }

    private static void HorizontalLine() {
        System.out.println("Enter matrix size: ");
        int nA = scanner.nextInt();
        int mA = scanner.nextInt();
        double[][] A = new double[nA][mA];
        for (int i = 0; i < nA; i++) {
            for (int j = 0; j < mA; j++) {
                A[i][j] = scanner.nextDouble();
            }
        }
        double[][] C = new double[nA][mA];
        for (int i=0;i<nA;i++){
            for (int j=0; j<mA;j++){
                C[i][j]=A[nA-1-i][j];
            }
        }
        for (int i = 0; i < nA; i++) {
            System.out.println();
            for (int j = 0; j < mA; j++) {
                System.out.print(C[i][j] + " ");
            }
        }
        System.out.println();
    }

    private static void VerticalLine() {
        System.out.println("Enter matrix size: ");
        int nA = scanner.nextInt();
        int mA = scanner.nextInt();
        double[][] A = new double[nA][mA];
        for (int i = 0; i < nA; i++) {
            for (int j = 0; j < mA; j++) {
                A[i][j] = scanner.nextDouble();
            }
        }
        double[][] C = new double[nA][mA];
        for (int i=0;i<nA;i++){
            for (int j=0; j<mA;j++){
                C[i][j]=A[i][mA-1-j];
            }
        }
        for (int i = 0; i < nA; i++) {
            System.out.println();
            for (int j = 0; j < mA; j++) {
                System.out.print(C[i][j] + " ");
            }
        }
        System.out.println();
    }

    private static void SideDiagonal() {
        System.out.println("Enter matrix size: ");
        int nA = scanner.nextInt();
        int mA = scanner.nextInt();
        double[][] A = new double[nA][mA];
        for (int i = 0; i < nA; i++) {
            for (int j = 0; j < mA; j++) {
                A[i][j] = scanner.nextDouble();
            }
        }
        double[][] C = new double[nA][mA];
        for (int i=0;i<nA;i++){
            for (int j=0; j<mA;j++){
                C[i][j]=A[mA-1-j][nA-1-i];
            }
        }
        for (int i = 0; i < nA; i++) {
            System.out.println();
            for (int j = 0; j < mA; j++) {
                System.out.print(C[i][j] + " ");
            }
        }
        System.out.println();
    }

    private static void MainDiagonal() {
        System.out.println("Enter matrix size: ");
        int nA = scanner.nextInt();
        int mA = scanner.nextInt();
        int[][] A = new int[nA][mA];
        for (int i = 0; i < nA; i++) {
            for (int j = 0; j < mA; j++) {
                A[i][j] = scanner.nextInt();
            }
        }
        int[][] C = new int[nA][mA];
        for (int i=0;i<nA;i++){
            for (int j=0; j<mA;j++){
                C[j][i]=A[i][j];
            }
        }
        for (int i = 0; i < nA; i++) {
            System.out.println();
            for (int j = 0; j < mA; j++) {
                System.out.print(C[i][j] + " ");
            }
        }
        System.out.println();
    }

    private static void Multiply() {
        System.out.println("Enter size of first matrix: ");
        int nA = scanner.nextInt();
        int mA = scanner.nextInt();
        double[][] A = new double[nA][mA];
        for (int i = 0; i < nA; i++) {
            for (int j = 0; j < mA; j++) {
                A[i][j] = scanner.nextDouble();
            }
        }
        System.out.println("Enter size of second matrix: ");
        int nB = scanner.nextInt();
        int mB = scanner.nextInt();
        double[][] B = new double[nB][mB];
        for (int i = 0; i < nB; i++) {
            for (int j = 0; j < mB; j++) {
                B[i][j] = scanner.nextDouble();
            }
        }
        double[][] C = new double[nA][mB];
        double sum=0;
        if (mA!=nB || nA==0 || mA==0 || nB==0 || mB==0) System.out.println("ERROR");
        else {
            System.out.print("The multiplication result is: ");
            for (int i=0;i<nA;i++){
                System.out.println();
                for (int k=0; k<mB;k++) {
                    for (int j = 0; j < mA; j++) {
                        sum += A[i][j] * B[j][k];
                        if (j + 1 >= mA) {
                            C[i][k] = sum;
                            sum = 0;
                            System.out.print(C[i][k] + " ");
                        }
                    }
                }
            }
        }
        System.out.println();
    }

    private static void MultiplyConst() {
        int nA = scanner.nextInt();
        int mA = scanner.nextInt();
        int[][] A = new int[nA][mA];
        for (int i = 0; i < nA; i++) {
            for (int j = 0; j < mA; j++) {
                A[i][j] = scanner.nextInt();
            }
        }
        int c = scanner.nextInt();
        for (int i = 0; i < nA; i++) {
            System.out.println();
            for (int j = 0; j < mA; j++) {
                A[i][j] *= c;
                System.out.print(A[i][j] + " ");
            }
        }
        System.out.println();
    }

    private static void Add() {
        System.out.println("Enter size of first matrix: ");
        int nA = scanner.nextInt();
        int mA = scanner.nextInt();
        double[][] A = new double[nA][mA];
        for (int i = 0; i < nA; i++) {
            for (int j = 0; j < mA; j++) {
                A[i][j] = scanner.nextDouble();
            }
        }
        System.out.println("Enter size of second matrix: ");
        int nB = scanner.nextInt();
        int mB = scanner.nextInt();
        double[][] B = new double[nB][mB];
        for (int i = 0; i < nB; i++) {
            for (int j = 0; j < mB; j++) {
                B[i][j] = scanner.nextDouble();
            }
        }
        if (nA != nB || mA != mB) {
            System.out.println("ERROR");
        } else {
            for (int i = 0; i < nA; i++) {
                for (int j = 0; j < mA; j++) {
                    A[i][j] += B[i][j];
                }
            }
            for (int i = 0; i < nA; i++) {
                System.out.println();
                for (int j = 0; j < mA; j++) {
                    System.out.print(A[i][j] + " ");
                }
            }
        }
        System.out.println();
    }
}

