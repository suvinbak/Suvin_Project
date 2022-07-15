package ToDoList_console;

import java.util.ArrayList;

public class ToDoList_VO extends Object{

    //변수
    private String toDo;
    private ArrayList<String> toDoArrayList = new ArrayList<>();

    public void setToDo(String toDo){

        this.toDo = toDo;
    }

    public String getToDo(){
        return toDo;
    }

    public void addToDo(){
        toDoArrayList.add(toDo);
    }

    public String getArrayList(int i){
        return toDoArrayList.get(i);
    }

    public int getArraySize(){
        return toDoArrayList.size();
    }

    public String removeArrayList(int i){
        return toDoArrayList.remove(i);
    }

}
