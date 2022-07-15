package ToDoList_console;

import java.util.Scanner;

public class ToDoList_main {
    public static void main(String[] args) {

        boolean isStop = false;
        Scanner scan = new Scanner(System.in);

        do{
            System.out.println("번호를 입력하세요.");
            System.out.println("1. 할 일 입력");
            System.out.println("2. 할 일 목록");
            System.out.println("3. 할 일 삭제");
            System.out.println("4. 종료하기");

            int menu = scan.nextInt();

            switch (menu){
                case 1: ToDoList_SCV.writeArticle(scan); break;
                case 2: ToDoList_SCV.ToDoList_listArticle(scan); break;
                case 3: ToDoList_SCV.ToDoList_removeArticle(scan); break;
                case 4: isStop = true;
            }

        } while (!isStop);

    }
}
