package AccountBook;

import java.util.Scanner;

public class AccountBook_main {

    public static void main(String[] args) {

        AccountBook_SCV.newFile();

        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

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
                case 1 :AccountBook_SCV.saveList(scanner); break;
                case 2 :
                case 3 :
                case 4 :
                case 5 :
                case 6 : exit = true;
            }
        } while (!exit);

    }

}
