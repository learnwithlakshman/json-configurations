package com.careerit.configurations.enumexamples;

enum MathOperation{
    ADD,SUB,MUL,DIV,MOD
}

enum ReportTypes{
    PDF,XLSX,CSV;
}

class MathCalc{

        public double perform(double a, double b, MathOperation ope){
            switch (ope){
                case ADD: return a + b;
                case SUB: return a - b;
                case MUL: return a * b;
                case DIV: return a / b;
                case MOD: return a % b;
                default:
                    throw new IllegalArgumentException("Unknown operation");
            }
        }
}

public class EnumExampleWithMathOperations {

    public static void main(String[] args) {
        MathCalc obj = new MathCalc();
            double a = 10;
            double b = 20;
            double res = obj.perform(a,b,MathOperation.MUL);
            System.out.println("Result :"+res);
    }
}
