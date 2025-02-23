package machine;

import java.util.Scanner;


class Ingredients {
    private int disposableCups = 9;
    private int milkAmount;
    private int coffeeAmount;
    private int waterAmount;

    private int money;



    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getDisposableCups() {

        return disposableCups;
    }

    public boolean setDisposableCups(int disposableCups) {
        if(disposableCups<0){
            System.out.println("Sorry, not enough cups!");
            return false;
        }
        this.disposableCups = disposableCups;
        return true;
    }


    public int getMilkAmount() {
        return milkAmount;
    }

    public boolean setMilkAmount(int milkAmount) {
        if(milkAmount<0){
            System.out.println("Sorry, not enough milk!");
            return false;
        }
        this.milkAmount = milkAmount;
        return true;
    }

    public int getCoffeeAmount() {
        return coffeeAmount;
    }

    public boolean setCoffeeAmount(int coffeeAmount) {
        if(coffeeAmount<0){
            System.out.println("Sorry, not enough coffee!");
            return false;
        }
        this.coffeeAmount = coffeeAmount;
        return true;
    }

    public int getWaterAmount() {
        return waterAmount;
    }

    public boolean setWaterAmount(int waterAmount) {
        if(waterAmount<0){
            System.out.println("Sorry, not enough water!");
            return false;
        }
        this.waterAmount = waterAmount;
        return true;
    }

    public Ingredients(int milkAmount, int coffeeAmount, int waterAmount, int money) {
        this.milkAmount = milkAmount;
        this.coffeeAmount = coffeeAmount;
        this.waterAmount = waterAmount;
        this.money = money;
    }
}

public class CoffeeMachine {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        CoffeeMachine coffeeMachine = new CoffeeMachine();
        int coffeeMade =0;

        int optionCoffee;
        Ingredients ingredients = new Ingredients(540, 120, 400, 550);
        boolean continueProgram = true;

        do {
            System.out.println("Write action (buy, fill, take, clean, remaining, exit): ");
            String input;
            do {
                input = scan.next();
                if(coffeeMade>=10)System.out.println("I need cleaning!");
            } while (coffeeMade >= 10 && !input.equals("clean"));

            switch (input) {
                case "buy":
                    System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 " +
                            "- cappuccino, back - to main menu:  ");
                    if (scan.hasNextInt()) {
                        optionCoffee = scan.nextInt();
                        coffeeMachine.buyCoffee(ingredients, optionCoffee);
                        coffeeMade++;
                    } else if (scan.next().equals("back")) {
                        break;
                    }

                    break;
                case "take":
                    System.out.println("i gave you $" + ingredients.getMoney());
                    ingredients.setMoney(0);
                    break;
                case "fill":
                    coffeeMachine.fillMachineState(ingredients);
                    break;
                case "remaining":
                    coffeeMachine.infoMachineState(ingredients);
                    break;
                case "exit":
                    continueProgram = false;
                    break;
                case "clean":
                    System.out.println("I have been cleaned!");
                    coffeeMade = 0;
                    break;
                default:
                    break;
            }
        } while (continueProgram);


    }

    void buyCoffee(Ingredients ingredients, int optionCoffee) {

        CoffeeProducts coffee = new CoffeeProducts();


        switch (optionCoffee) {

            //espresso
            case 1:
                prepareCoffee(ingredients,coffee.espresso);
                break;
            //late
            case 2:
                prepareCoffee(ingredients,coffee.latte);
                break;
            //cappuccino
            case 3:
                prepareCoffee(ingredients,coffee.cappuccino);
                break;

            default:
                break;
        }

    }

    void infoMachineState(Ingredients ingredients) {
        System.out.println("The coffee machine has: ");
        System.out.println(ingredients.getWaterAmount() + " ml of water");
        System.out.println(ingredients.getMilkAmount() + " ml of milk");
        System.out.println(ingredients.getCoffeeAmount() + " g of coffee beans");
        System.out.println(ingredients.getDisposableCups() + " disposable cups");
        System.out.println("$"+ingredients.getMoney() + " of money");
    }

    void fillMachineState(Ingredients ingredients) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Write how many ml of water you want to add: ");
        ingredients.setWaterAmount(ingredients.getWaterAmount() + scan.nextInt());
        System.out.println("Write how many ml of milk you want to add: ");
        ingredients.setMilkAmount(ingredients.getMilkAmount() + scan.nextInt());
        System.out.println("Write how many grams of coffee you want to add: ");
        ingredients.setCoffeeAmount(ingredients.getCoffeeAmount() + scan.nextInt());
        System.out.println("Write how many disposable cups you want to add: ");
        ingredients.setDisposableCups(ingredients.getDisposableCups() + scan.nextInt());
    }
    void prepareCoffee(Ingredients ingredients,Ingredients coffeeType) {

        if(ingredients.getWaterAmount()-coffeeType.getWaterAmount()>=0&&ingredients.getMilkAmount()-coffeeType.getMilkAmount()>=0
        &&ingredients.getCoffeeAmount()-coffeeType.getCoffeeAmount()>=0&&ingredients.getDisposableCups()-1>=0) {
            if (!ingredients.setDisposableCups(ingredients.getDisposableCups() - 1)) return;
            if (!ingredients.setWaterAmount(ingredients.getWaterAmount() - coffeeType.getWaterAmount())) return;
            if (!ingredients.setMilkAmount(ingredients.getMilkAmount() - coffeeType.getMilkAmount())) return;
            if (!ingredients.setCoffeeAmount(ingredients.getCoffeeAmount() - coffeeType.getCoffeeAmount())) return;
            else ingredients.setMoney(ingredients.getMoney() + coffeeType.getMoney());
            System.out.println("I have enough resources, making you a coffee!");

        }else if(ingredients.getWaterAmount()-coffeeType.getWaterAmount()<0){
            System.out.println("Sorry, not enough water!");
        } else if (ingredients.getMilkAmount()-coffeeType.getMilkAmount()<0) {
            System.out.println("Sorry, not enough milk!");
        } else if (ingredients.getCoffeeAmount()-coffeeType.getCoffeeAmount()<0) {
            System.out.println("Sorry, not enough coffee!");
        } else if (ingredients.getDisposableCups()-1<0) {
            System.out.println("Sorry, not enough disposable cups!");
        }

    }

}

