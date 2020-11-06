package regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class mainDriver {
    public static void main(String[] args) {
        Pattern phonePattern = Pattern.compile("(\\d{3})-(\\d{3})-(\\d{4})");
        Matcher phoneMatcher1 = phonePattern.matcher("abcd800-555-1234wxyz");
        Matcher phoneMatcher2 = phonePattern.matcher("964-395-7240");

        if(phoneMatcher1.matches())
            System.out.println("Match is "+phoneMatcher1.group(0));
//        else if(phoneMatcher2.matches())
//            System.out.println("Match is "+phoneMatcher1.group(0));
        else
            System.out.println("Nothing matched");
    }
}
