package lambda;

public class mainDriver {
    public static void main(String[] args) {
        //old school
        MathOperation isEven = new MathOperation() {
            @Override
            public boolean unaryOperation(int num) {
                return num%2==0;
            }
        };
        System.out.println(isEven.unaryOperation(25));
        System.out.println(isEven.unaryOperation(20));

        //lambda way
        MathOperation isEvenBetter= num -> num%2==0;
        System.out.println(isEven.unaryOperation(25));
        System.out.println(isEven.unaryOperation(20));


    }
}
