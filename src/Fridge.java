import java.util.ArrayList;

public class Fridge {

    public static ArrayList<Burger> prepareBurgers(){
        ArrayList<Burger> allBurgers = new ArrayList<>();

        BasicBurger bb = new BasicBurger();
        HealthyBurger hb = new HealthyBurger();
        DeluxeBurger db = new DeluxeBurger();

        allBurgers.add(bb);
        allBurgers.add(hb);
        allBurgers.add(db);

        return allBurgers;
    }

    public static ArrayList<Topping> prepareToppings(){
        ArrayList<Topping> allToppings = new ArrayList<>();

        Topping tomato = new Topping("_Tomato",1.27);
        Topping lettuce = new Topping("_Lettuce",2.37);
        Topping cheese = new Topping("_Cheese",2.77);
        Topping carrot = new Topping("_Carrot",2.27);
        Topping greenpepper = new Topping("_Green Pepper",0.57);
        Topping olives = new Topping("_Olives",1.77);

        allToppings.add(tomato);
        allToppings.add(lettuce);
        allToppings.add(cheese);
        allToppings.add(carrot);
        allToppings.add(greenpepper);
        allToppings.add(olives);

        return allToppings;
    }

}
