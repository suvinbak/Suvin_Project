package ToDoList_console;

import java.util.Scanner;

public class ToDoList_SCV {

    static ToDoList_VO toDoList_VO = new ToDoList_VO();

    //할 일 입력 메소드
    public static void writeArticle(Scanner scan){
        System.out.println("할 일을 입력하세요.");
        System.out.print("> ");
        String toDo = scan.next();

        toDoList_VO.setToDo(toDo);
        toDoList_VO.addToDo();
    }

    //할 일 목록 출력
    public static void ToDoList_listArticle(Scanner scan){
        if(toDoList_VO.getArraySize()>0){
            for(int i = 0; i < toDoList_VO.getArraySize(); i++){
                System.out.println((i+1) + " : " + toDoList_VO.getArrayList(i));
            }
        } else {
            System.out.println("등록된 일정이 없습니다.");
        }
    }

    //할 일 삭제 메소드
    public static void ToDoList_removeArticle(Scanner scan){
        if(toDoList_VO.getArraySize()>0){
            for(int i = 0; i < toDoList_VO.getArraySize(); i++){
                System.out.println((i+1) + " : " + toDoList_VO.getArrayList(i));
            }
        } else {
            System.out.println("등록된 일정이 없습니다.");
        }
        int removeNum = scan.nextInt();
        toDoList_VO.removeArrayList(removeNum-1);
    }

}
