/*
* Фомина Мария Игоревна, группа 5
* Задание номер 13
* Условие: Выполняем два варианты задачи:
А) Используя стандартные инструкции JAVA
B) Используя класс BigDecimal
Разработать консольное приложение на Java.
Функция представлена в виде своего ряда Тейлора. Вычислить приближённое значение суммы этого бесконечного ряда. Вычисления заканчивать, когда очередное слагаемое окажется по модулю меньше заданного числа . Вид этого числа определяется  следующим условием:
  = 10-k, где k – натуральное число.
Сравнить полученный результат со значением, вычисленным через стандартные функции.
Значения x и k ввести с клавиатуры.
Вывод результата осуществить с тремя знаками после десятичной точки.
Условие примера: sinh(x)=(e^x-e^(-x))/2=x+x^3/3!++x^5/5!
* */
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class Main {
    public  static BigDecimal myPow(BigDecimal x, int y) {
        BigDecimal result= BigDecimal.ONE;
        if(y>0){
            for(int i=1; i<=y; i++){
                result=result.multiply(x);
            }
        }
        else if(y<0){
            for(int i=0; i<=(-y); i++) {
                x=x.divide(BigDecimal.TEN, MathContext.DECIMAL128);
            }
            result=x;
        }
        return result;
    }
    public static BigDecimal my_func(BigDecimal x, BigDecimal e)
    {
        BigDecimal n=BigDecimal.ONE;
        BigDecimal res=x;
        BigDecimal k=x;
        while(k.abs().compareTo(e)>0)
        {

            k=k.multiply(myPow(x,2)).divide(n.add(BigDecimal.ONE).multiply(n.add(new BigDecimal("2"))),MathContext.DECIMAL128);
            res=res.add(k);
            n=n.add(new BigDecimal("2"));
        }
        return res;
    }
    public static void main(String[] args) {
        InputStreamReader isr=new InputStreamReader(System.in);
        BufferedReader br=new BufferedReader(isr);
        try {
            System.out.println("Введите x: ");
            String l = br.readLine();
            BigDecimal x = new BigDecimal(l);
            System.out.println("Введите натуральное число k: ");
            String sl = br.readLine();
            int ourDegree=Integer.parseInt(sl);
            ourDegree=-ourDegree;
            BigDecimal e= myPow(BigDecimal.TEN, ourDegree);
            double res_1=Math.sinh(x.doubleValue());
            System.out.print("Результат встроенной функции: ");
            System.out.println(String.format("%.3f", res_1));
            BigDecimal res_2=my_func(x,e);
            System.out.println("Результат собственных расчетов с BigDecimal: "+res_2.setScale(3, RoundingMode.HALF_UP));

        }
        catch (NumberFormatException e) {
            System.out.println("Не число");
        }
        catch (IOException e) {
            System.out.println("Ошибка чтения с клавиатуры");
        }
    }
}