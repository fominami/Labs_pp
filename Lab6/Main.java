//Фомина Мария Игоревна
//Лабораторная 6
// Условие: Необходимо разработать систему для обработки заказов в кафе,
// используя паттерн "Адаптер". Существующий класс CoffeeMachine умеет готовить кофе,
// чай и горячий шоколад. Нужно создать интерфейс OrderProcessor и адаптер CoffeeMachineAdapter,
// чтобы обрабатывать заказы клиентов через этот интерфейс.
// Тест: Сколько напитков вы хотите заказать?
//3
//Что вы выберете: coffee, tea or hot chocolate?
//coffee
//Какой тип напитка?
//latte
//Что вы выберете: coffee, tea or hot chocolate?
//tea
//Какой тип напитка?
//green
//Что вы выберете: coffee, tea or hot chocolate?
//hot chocolate
//Какой тип напитка?
//sweet
//Making a latte coffee.
//Making a green tea.
//Making a hot chocolate.
package org.example;

import java.util.ArrayList;
import java.util.Scanner;

interface OrderProcessor {  // Client Interface
    void processOrder(String orderType, String itemType);
}

class CoffeeMachine {  // Service
    void makeCoffee(String type) {
        System.out.println("Making a " + type + " coffee.");
    }

    void makeTea(String type) {
        System.out.println("Making a " + type + " tea.");
    }

    void makeHotChocolate() {
        System.out.println("Making a hot chocolate.");
    }
}

class CoffeeMachineAdapter implements OrderProcessor {  // Adapter
    private final CoffeeMachine coffeeMachine;

    CoffeeMachineAdapter(CoffeeMachine coffeeMachine) {
        this.coffeeMachine = coffeeMachine;
    }

    @Override
    public void processOrder(String orderType, String itemType) {
        switch (orderType.toLowerCase()) {
            case "coffee":
                coffeeMachine.makeCoffee(itemType);
                break;
            case "tea":
                coffeeMachine.makeTea(itemType);
                break;
            case "hot chocolate":
                coffeeMachine.makeHotChocolate();
                break;
            default:
                System.out.println("Нет такого напитка: " + orderType);
                break;
        }
    }
}

class CafeClient {  // Client
    private final OrderProcessor orderProcessor;

    CafeClient(OrderProcessor orderProcessor) {
        this.orderProcessor = orderProcessor;
    }

    void makeOrder(String orderType, String itemType) {
        orderProcessor.processOrder(orderType, itemType);
    }
}
class Order {
    public String name;
    public String type;

    public Order(String first, String second) {
        this.name = first;
        this.type = second;
    }
}
public class Main {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        CoffeeMachine coffeeMachine = new CoffeeMachine();
        OrderProcessor coffeeMachineAdapter = new CoffeeMachineAdapter(coffeeMachine);
        CafeClient client = new CafeClient(coffeeMachineAdapter);
        System.out.println("Сколько напитков вы хотите заказать?");
        int n=scanner.nextInt();
        scanner.nextLine();
        ArrayList<Order> orders=new ArrayList<>();
        for(int i=0; i<n;i++)
        {
            System.out.println("Что вы выберете: coffee, tea or hot chocolate?");
            String name=scanner.nextLine();
            System.out.println("Какой тип напитка?");
            String type=scanner.nextLine();
            Order o=new Order(name,type);
            orders.add(o);
        }
        for(int i=0; i<n;i++) {

            client.makeOrder(orders.get(i).name, orders.get(i).type);

        }
    }
}
