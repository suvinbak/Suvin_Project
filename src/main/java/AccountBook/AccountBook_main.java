package AccountBook;

import java.util.Scanner;

public class AccountBook_main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        AccountBook_SCV.dbConnection();

        do{
            System.out.println("메뉴를 입력하세요.");
            System.out.println("----- Menu -----");
            System.out.println("1. 입금하기");
            System.out.println("2. 출금하기");
            System.out.println("3. 내역확인");
            System.out.println("4. 내역삭제");
            System.out.println("5. 모두삭제");
            System.out.println("6. 종료하기");
            System.out.println("----------------");
            System.out.print("> ");

            int menu = scanner.nextInt();
            switch (menu){
                case 1 : AccountBook_SCV.saveList(); break;
                case 2 : AccountBook_SCV.usedList(); break;
                case 3 : AccountBook_SCV.selectList(); break;
                case 4 : AccountBook_SCV.deleteList(); break;
                case 5 : AccountBook_SCV.deleteAllList(); break;
                case 6 : exit = true;
            }
        } while (!exit);

    }

}
