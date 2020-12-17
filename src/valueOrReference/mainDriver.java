package valueOrReference;

public class mainDriver {

    public static void changeValue(User user){
        user.setName("Nitin");
        user.setStrength("Biology");
    }

    public static void foo(Dog d) {
        d.getName().equals("Max"); // true
        // change d inside of foo() to point to a new Dog instance "Fifi"
        d = new Dog("Fifi");
        d.getName().equals("Fifi"); // true
    }

    public static void main(String[] args) {
        // java is pass by reference, references are passed by value

        User user= new User("saurabh","physics");
        System.out.println(user);
        changeValue(user);
        System.out.println(user);


        //wait and wait
        //https://stackoverflow.com/questions/40480/is-java-pass-by-reference-or-pass-by-value
        Dog aDog = new Dog("Max");
        Dog oldDog = aDog;

        // we pass the object to foo
        foo(aDog);
        // aDog variable is still pointing to the "Max" dog when foo(...) returns
        aDog.getName().equals("Max"); // true
        aDog.getName().equals("Fifi"); // false
        //aDog == oldDog; // true

        /** another example
         *public static void main(String[] args) {
         *     Dog aDog = new Dog("Max");
         *     Dog oldDog = aDog;
         *
         *     foo(aDog);
         *     // when foo(...) returns, the name of the dog has been changed to "Fifi"
         *     aDog.getName().equals("Fifi"); // true
         *     // but it is still the same dog:
         *     aDog == oldDog; // true
         * }
         *
         * public static void foo(Dog d) {
         *     d.getName().equals("Max"); // true
         *     // this changes the name of d to be "Fifi"
         *     d.setName("Fifi");
         * }
         *
         **/
    }
}
