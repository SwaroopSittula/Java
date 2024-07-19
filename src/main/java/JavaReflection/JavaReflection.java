// https://docs.oracle.com/javase/tutorial/reflect/class/classMembers.html
package JavaReflection;

import java.lang.reflect.*;
import java.util.*;
import java.util.stream.Collectors;

class Student {
    private String name;
    private String id;
    private String email;

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setName(String firstname, String lastname) {
        this.name = firstname + " " + lastname;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

public class JavaReflection {

    static Class<Student> student = Student.class;

    /**
     * Get the name of the methods in the class in sorted order
     */
    static {
        // Class<?> class = Class.getClass("xyz-name");
        Method[] methods = student.getDeclaredMethods();

        ArrayList<String> methodList = new ArrayList<>();
        for (Method method : methods) {
            methodList.add(method.getName());
        }
        Collections.sort(methodList);
        for (String name : methodList) {
            System.out.println(name);
        }
    }


    /**
     * Check if the class has Method Overloading => one or more methods have same name different signature
     */
    static {
        Method[] methods = student.getDeclaredMethods();
        List<String> methodWithOverriding = Arrays.stream(methods)
                .collect(Collectors.groupingBy(Method::getName, Collectors.counting()))
                .entrySet().stream()
                .filter(entry -> entry.getValue() > 1)
                .map(Map.Entry::getKey)
                .toList();
        System.out.println("Methods having Override: " + methodWithOverriding);
    }

    public static void main(String[] args) {
    }
}




