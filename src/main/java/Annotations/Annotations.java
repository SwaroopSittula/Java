package Annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

// Custom Annotation
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface FamilyBudget {
    String userRole() default "GUEST";
    int budgetLimit() default 0;
}

class FamilyMember {
    @FamilyBudget(userRole = "SENIOR", budgetLimit = 100)
    public void seniorMember(int budget, int moneySpend) {
        System.out.println("Senior Member");
        System.out.println("Spend: " + moneySpend);
        System.out.println("Budget Left: " + (budget - moneySpend));
    }

    @FamilyBudget(userRole = "JUNIOR", budgetLimit = 50)
    public void juniorMember(int budget, int moneySpend) {
        System.out.println("Junior Member");
        System.out.println("Spend: " + moneySpend);
        System.out.println("Budget Left: " + (budget - moneySpend));
    }
}


public class Annotations {
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        int spend = 45;

        Class<FamilyMember> familyMemberClass = FamilyMember.class;
        for(Method method: familyMemberClass.getMethods()) {
            if(method.isAnnotationPresent(FamilyBudget.class)){
                FamilyBudget familyBudget = method.getAnnotation(FamilyBudget.class);
                String userRole = familyBudget.userRole();
                int budgetLimit = familyBudget.budgetLimit();

                if(spend < budgetLimit)
                    method.invoke(new FamilyMember(), budgetLimit, spend);
                else
                    System.out.println("Budget Limit Over");
            }
        }
    }
}
