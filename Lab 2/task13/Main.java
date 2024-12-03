/*Фомина Мария Игоревна
* Задание 13
* Условие: 13.	Найти максимальное из чисел, встречающихся в заданной матрице более одного раза.
* Идея решения: Заполняем  матрицу заданных размеров рандомно.
* Далее помещаем все элемменты в массив, сортируем его.
* Двигаясь с конца проверяем наличие дубликатов.
* Первое значение с конца, которое имеет дубликат, является искомым
* Тест:
* №1
* 67  96  72  79
3  36  31  28
20  6  59  63
32  61  49  84
Values dont't repeat
* №2
* Enter number of row
4
Enter number of columm
3
15  85  16
20  91  25
10  90  12
91  54  14
The biggest value in the matrix, which repeat more than once in the matrix:91*/
import java.util.Scanner;
import java.util.Random;
import java.util.Arrays;
public class Main {
    public static void main(String[] args) {
        Scanner scanner= new Scanner(System.in);
        Random random = new Random();

        System.out.println("Enter number of row");
        int m= scanner.nextInt();
        System.out.println("Enter number of columm");
        int n= scanner.nextInt();
        int mat[][]= new int[m][n];
        int[] mat_all=new int[n*m];
        int a=0;
        for(int i=0;i<m;i++)
        {
            for(int j=0;j<n;j++)
            {
                mat[i][j]=random.nextInt(100);// сощдание и вывод матрицы
                System.out.print(mat[i][j]+"  ");
            }
            System.out.println();
        }
        for(int i=0;i<m;i++)
        {
            for(int j=0;j<n;j++)
            {
                mat_all[a]=mat[i][j];//создание массива элементов матрицы
                a++;
            }
        }
        Arrays.sort(mat_all);//сортировка массива встроенной сортировкой
        int i=n*m-1;
        while(mat_all[i]!=mat_all[i-1])
        {
            i--;
            if(i==0)
            {
                System.out.println("Values dont't repeat");//в случае если элементы не повторяются
                return;
            }
        }
        if(i>0)
        {
            System.out.println("The biggest value in the matrix, which repeat more than once in the matrix:"+mat_all[i]);
        }

    }
}