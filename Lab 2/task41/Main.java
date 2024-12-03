/*Фомина Мария Игоревна
*Задание 41
* Усдовие: 41.	Взаимно однозначное отображение элементов матрицы на себя можно задать с помощью двух целочисленных матриц:
* в первой указывать номер строки, куда переходит данный элемент, а во второй — номер столбца.
* Построить две матрицы, задающие отражение каждого элемента матрицы  на симметричный ему относительно главной диагонали.
* Идея решения: Значения, которое должна содержать матрица строк содержаться в координате элемента.
*  Это значение есть номер столбца, в котором стоит элемент.
* Аналогично с матрицей столбцов. Нужное значение это номер строки, в которой стоит элемент.
* Тест:
* Enter size
4
Исходная матрица:
69 31 92 79
76 77 72 22
74 9 1 94
0 56 8 62
Матрица строк:
0 1 2 3
0 1 2 3
0 1 2 3
0 1 2 3
Матрица столбцов:
0 0 0 0
1 1 1 1
2 2 2 2
3 3 3 3
 */
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;
import java.util.Random;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.println("Enter size");
        int m = scanner.nextInt();
        Vector<Vector<Integer>> matrix = new Vector<>();
        for (int i = 0; i < m; i++) {
            matrix.add(new Vector<>());
            for (int j = 0; j < m; j++) {
                matrix.get(i).add(random.nextInt(100));//заполнение матрицы
            }
        }
        Vector<Vector<Integer>> rows = new Vector<>();
        Vector<Vector<Integer>> cols = new Vector<>();
        for (int i = 0; i < m; i++) {
            rows.add(new Vector<>());
            for (int j = 0; j < m; j++) {
                rows.get(i).add(j); //заполнение матрицы
            }
        }
        for (int i = 0; i < m; i++) {
            cols.add(new Vector<>());
            for (int j = 0; j < m; j++) {
                cols.get(i).add(i); //заполнение матрицы
            }
        }

        // Вывод результатов
        System.out.println("Исходная матрица:");
        printMatrix(matrix);

        System.out.println("Матрица строк:");
        printMatrix(rows);

        System.out.println("Матрица столбцов:");
        printMatrix(cols);
    }

    // Метод для вывода матрицы
    private static void printMatrix(Vector<Vector<Integer>> matrix) {
        for (Vector<Integer> row : matrix) {
            for (Integer value : row) {
                System.out.print(value + " ");
            }
            System.out.println();
        }
    }
}