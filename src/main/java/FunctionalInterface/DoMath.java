package FunctionalInterface;

/***
 * An Interface that contains exactly one abstract method is known as functional interface.
 * It can have any number of default, static methods but can contain only one abstract method.
 * It can also declare methods of object class.
 * Functional Interface is also known as Single Abstract Method Interfaces or SAM Interfaces.
 * It is a new feature in Java, which helps to achieve functional programming approach.
 */

@FunctionalInterface
interface BasicMath{
        // abstract method
        Integer calculate(Integer x, Integer y);

        //Any number of static/default methods are allowed
        static Integer reverseRecursive(Integer x, Integer rev){
            if(x==0)
                return rev;
            rev = (rev * 10) + (x%10);
            return reverseRecursive(x/10, rev);
        }

        static Integer reverseRecursive(Integer x){
            return  reverseRecursive(x,0);
        }

        static Integer reverse(Integer x){
            int rev = 0;
            while(x!=0){
                rev = (rev * 10) + (x%10);
                x /= 10;
            }
            return rev;
        }

        // It can contain any number of Object class methods.
        int hashCode();
        String toString();
        boolean equals(Object obj);
}


/***
 * A functional interface can extend another interface only when it does not have any abstract method.
 * or Same abstract method as the parent
 */
@FunctionalInterface
interface ComplexMath extends BasicMath{
    Integer calculate(Integer x, Integer y);
    //Integer compute(Integer x, Integer y);
}


/***
 * Defining the abstract Method of Functional Interface using lambda
 */
public class DoMath {
    public static void main(String[] args) {

        BasicMath addition = Integer::sum;
        BasicMath subtraction = (Integer x, Integer y) -> (x-y);
        BasicMath multiplication = (Integer x, Integer y) -> (x*y);
        BasicMath division = (Integer x, Integer y) -> (x/y);
        BasicMath maximum = Math::max;
        BasicMath minimum = Math::min;

        Integer x = 6;
        Integer y = 2;

        System.out.println(addition.calculate(x,y));
        System.out.println(subtraction.calculate(x,y));
        System.out.println(multiplication.calculate(x,y));
        System.out.println(division.calculate(x,y));
        System.out.println(maximum.calculate(x,y));
        System.out.println(minimum.calculate(x,y));

        System.out.println(BasicMath.reverse(x*y));
        System.out.println(BasicMath.reverseRecursive(-2341));

        System.out.println(addition);
    }

}
