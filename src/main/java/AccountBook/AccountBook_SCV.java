package AccountBook;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class AccountBook_SCV {

    static int total = 0;
    static String path = System.getProperty("user.dir") + File.separator + "accountBook";

    public static void newFile(){
        String os =System.getProperty("os.name").toLowerCase();

        File folder = new File(path);
        File file = new File(folder, "accountBook.txt");

        if(os.contains("win")) {
            System.out.println("OS : Windows");
        } else if(os.contains("mac")) {
            System.out.println("OS : Mac");
        } else if(os.contains("nix")||os.contains("nux")||os.contains("aix")) {
            System.out.println("OS : Unix");
        } else if(os.contains("linux")) {
            System.out.println("OS : Linux");
        } else if(os.contains("sunos")) {
            System.out.println("OS : Solaris");
        }

        if(!folder.exists()){
            try{
                folder.mkdir();
                System.out.println("폴더가 생성되었습니다.");
            } catch (Exception e){
                e.printStackTrace();
            }
        }

        if(!file.exists()){
            try{
                file.createNewFile();
                System.out.println("정보가 생성되었습니다.");
            } catch (Exception e){
                e.printStackTrace();
            }
        } else {
            System.out.println("저장된 정보가 이미 있습니다.");
        }

    }

    public static void saveList(Scanner scanner){

        String path2 = path + File.separator + "accountBook.txt";
        ArrayList<String> list = new ArrayList<String>();

        try {
            FileWriter fw = new FileWriter(path2);
            BufferedWriter writer = new BufferedWriter(fw);

            System.out.print("내용 > ");
            String saveList = scanner.next();
            list.add(0, saveList);

            System.out.print("금액 > ");
            String money1 = scanner.next();
            list.add(1, money1);

            int money2 = Integer.parseInt(list.get(1));
            total += money2;

            list.add(2, String.valueOf(total));

            writer.write(String.valueOf(list));

            writer.close();
        } catch (Exception e){
            e.printStackTrace();
        }

    }

}
