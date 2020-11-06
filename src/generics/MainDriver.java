package generics;

public class MainDriver {
    public static void main(String[] args) {
        Param<Integer> integerParam = new Param<>();
        integerParam.setValue(10);
        System.out.println("Value is "+integerParam.getValue());

        Param<String> stringParam= new Param<>();
        stringParam.setValue("Hi");
        System.out.println("Value is "+stringParam.getValue());

        MultiGenericParam<String, String> aParam = new MultiGenericParam<>("value1",
                "value2");
        MultiGenericParam<String, Double> dayOfWeekDegrees = new MultiGenericParam<>("saurabh",
                2.6);
        System.out.println("aparam :"+dayOfWeekDegrees.getFirstParam()+" "+dayOfWeekDegrees.getSecondParam());
    }
}
