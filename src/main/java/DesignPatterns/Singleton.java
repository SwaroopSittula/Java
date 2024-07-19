package DesignPatterns;

class SingletonClass {
    private static SingletonClass singleton = null;
    public String str = "hello world";
    private SingletonClass() {}
    public static SingletonClass getSingleInstance() {
        synchronized(SingletonClass.class) {
            if(singleton == null)
                singleton = new SingletonClass();
        }
        return singleton;
    }
}

// Singleton Pattern
public class Singleton {
    public static void main(String[] args) {
        SingletonClass s1 = SingletonClass.getSingleInstance();
        SingletonClass s2 = SingletonClass.getSingleInstance();
        s1.str = "Updated String in one variable";
        System.out.println(s1==s2);
        System.out.printf("s1.str: %s \ns2.str: %s", s1.str, s2.str);
    }
}