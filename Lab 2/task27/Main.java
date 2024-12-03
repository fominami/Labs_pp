/*Фомина Мария Игроревна
* Задание 27
* Условие:27.	Определить, явля¬ется ли действительная матрица размера mxn  ортонормированной,
*  т.е. такой, в которой скалярное  произведение каждой пары различных строк равно 0,
*  а ска-лярное произведение каждой строки на себя равно 1.
*  Вывести на экран соответствующее сообщение.
*Идея решение: Я разделаила задание на 2 условные части. Сначала я проверила,
* чтобы  а ска-лярное произведение каждой строки на себя равно 1. Затем скалярное  произведение каждой пары различных строк равно 0.
*В обоих случаях я считала скалярную сумму пока она соответствовала условию. Как только условие не выполнялось я меняла булевое значние флага.
* тесты: Тесты проводились с значениями 0,1 в матрице, для увеличения шанса получения ортонормированной.
* №1
Enter number of row
2
Enter number of columm
2
[0, 1]
[1, 0]
This matrix is orthonormal!
* №2
* Enter number of row
3
Enter number of columm
3
[1, 0, 1]
[1, 1, 0]
[1, 1, 1]
2
This matrix is NOT orthonormal*/
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

public class Main {
    public static void main(String[] args) {
        Scanner scanner= new Scanner(System.in);
        Random random = new Random();

        System.out.println("Enter number of row");
        int m= scanner.nextInt();
        System.out.println("Enter number of columm");
        int n= scanner.nextInt();
        ArrayList<ArrayList<Integer>> matrix = new ArrayList<>();
        for (int i = 0; i <m; i++) {
            matrix.add(new ArrayList<>());
            for (int j = 0; j < n; j++) {
                matrix.get(i).add(random.nextInt(2));//заполнение матрицы
            }
        }
        for (ArrayList<Integer> row : matrix) {
            for (Integer value : row) {
                System.out.print(value + " ");
            }
            System.out.println();
        }
        int sum=0;
        int k=0;
        boolean flag=true;
        //Проверка №1.ска¬лярное произведение каждой строки на себя равно 1
        for(int i=0;i<m;i++)
        {
            while(k<n)
            {
                sum+=matrix.get(i).get(k)*matrix.get(i).get(k);
                k++;

            }
            k=0;
            if(sum!=1)
            {
                System.out.println("This matrix is NOT orthonormal");//расчет скалярного произведения
                flag=false;
                return;
            }
            sum=0;
        }
        sum=0;
        k=0;
        //При условии выполнения первого условия №2 условие.скалярное  произведение каждой пары различных строк равно 0
        if(flag==true) {
            for (int i = 0; i < m - 1; i++) {
                for (int j = i + 1; j < m; j++) {
                    while (k < n) {
                        sum += matrix.get(i).get(k) * matrix.get(j).get(k);//расчет скалярного произведения
                        k++;

                    }
                    k=0;
                    if (sum != 0) {
                        System.out.println("This matrix is NOT orthonormal ");
                        flag = false;
                        return;
                    }
                    sum = 0;
                }
            }
        }
        if(flag==true)//при сохранении флага в True. То есть все условия были выполнены
        {
            System.out.println("This matrix is orthonormal!");
        }
    }
}