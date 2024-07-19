package DesignPatterns;

interface Food {
    String getType();
}

class Pizza implements Food {
    public String getType() {
        return "Someone ordered a Fast Food!";
    }
}

class Cake implements Food {
    @Override
    public String getType() {
        return "Someone ordered a Dessert!";
    }
}

// A factory is simply an object that returns another object by some method call, which is assumed to be "new".
class FoodFactory {
    public Food getFood(String order) {
        Food food = null;
        if (order.equals("pizza"))
            food = new Pizza();
        else if (order.equals("cake"))
            food = new Cake();
        return food;
    }
}


// Factory Pattern
public class Factory {
    public static void main(String[] args) {
        FoodFactory foodFactory = new FoodFactory();
        Food pizza = foodFactory.getFood("pizza");
        System.out.println("The factory returned " + pizza.getClass());
        System.out.println(pizza.getType());

        Food cake = foodFactory.getFood("cake");
        System.out.println("The factory returned " + cake.getClass());
        System.out.println(cake.getType());
    }
}
