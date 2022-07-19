package AccountBook;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.annotation.ElementType;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;

public class AccountBook_SCV {

    static int no, used, saved, total;
    static ResultSet resultSet = null;
    static String contents, reg_date;
    static BufferedReader bufferedWriter = new BufferedReader(new InputStreamReader(System.in));
    static String sql;
    static Connection connection = null;
    static PreparedStatement preparedStatement = null;
    static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void dbConnection(){
        String driver = "oracle.jdbc.OracleDriver";
        String url = "jdbc:oracle:thin:@dumdum_medium?TNS_ADMIN=//Users/apple/Downloads/Wallet_dumdum";
        String id = "admin";
        String pw = "Tnqls072535@";

        // 드라이버 로딩
        try {
            Class.forName(driver);
        }catch (Exception exception) {
            System.out.println("드라이버 연결 실패");
            exception.printStackTrace();
        }

        // 커넥션 설정
        try{
            connection = DriverManager.getConnection(url, id, pw);
        } catch (Exception exception){
            System.out.println("커넥션 설정 실패");
            exception.printStackTrace();
        }
    }

    public static void saveList(){

        try {
            System.out.println("입금된 내역을 입력하세요.");
            System.out.print("> ");
            contents = bufferedWriter.readLine().trim();
            System.out.println("입금된 금액을 입력하세요.");
            System.out.print("> ");
            saved = Integer.parseInt(bufferedWriter.readLine().trim());

            sql = "SELECT nvl(total,0), LAST_VALUE(total) OVER() as lagTotal from ACCOUNTBOOK";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                int lagTotal = resultSet.getInt("lagTotal");

                if(lagTotal == 0){
                    total = saved;
                } else {
                    total = lagTotal+saved;
                }
            }
            System.out.println("잔액은 " + total + "원입니다.");

            sql = "INSERT into ACCOUNTBOOK (no, contents, used, saved, total, reg_date) ";
            sql += " values (AccountBook_seq.nextval, ?, 0, ?, ?, sysdate)";
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, contents);
            preparedStatement.setInt(2, saved);
            preparedStatement.setInt(3, total);

            int result = preparedStatement.executeUpdate();

            if (result == 1) {
                System.out.println("저장되었습니다.");
            } else {
                System.out.println("저장에 실패했습니다.");
            }
        } catch(Exception exception) {
            exception.printStackTrace();
        } finally {

        }
    }

    public static void usedList(){

        try {
            //테이블에 저장할 값 입력받기
            System.out.println("사용한 내역을 입력하세요.");
            contents = bufferedWriter.readLine().trim();
            System.out.println("사용한 금액을 입력하세요.");
            used = Integer.parseInt(bufferedWriter.readLine().trim());

            sql = "SELECT total, LAST_VALUE(total) OVER() as lagTotal from ACCOUNTBOOK";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                int lagTotal = resultSet.getInt("lagTotal");

                if(lagTotal == 0){
                    total = used;
                } else {
                    total = lagTotal-used;
                }
            }
            System.out.println("잔액은 " + total + "원입니다.");

            sql = "INSERT into ACCOUNTBOOK (no, contents, used, saved, total, reg_date) ";
            sql += " values (AccountBook_seq.nextval, ?, ?, 0, ?, sysdate)";
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, contents);
            preparedStatement.setInt(2, used);
            preparedStatement.setInt(3, total);

            int result = preparedStatement.executeUpdate();

            if (result == 1) {
                System.out.println("저장되었습니다.");
            } else {
                System.out.println("저장에 실패했습니다.");
            }

        } catch (Exception exception){
            exception.printStackTrace();
        }

    }

    public static void selectList(){

        try{
            sql = "select * from ACCOUNTBOOK order by no asc";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            System.out.println("번호 \t 내역 \t 사용한 금액 \t 입금된 금액 \t 잔액 \t 시간");
            System.out.println("----------------------------------------------------");

            while(resultSet.next()){
                no = resultSet.getInt("no");
                contents = resultSet.getString("contents");
                used = resultSet.getInt("used");
                saved = resultSet.getInt("saved");
                total = resultSet.getInt("total");
                reg_date = resultSet.getString("reg_date");

                System.out.printf(" %d \t %s \t %d \t %d \t %d \t %s", no, contents, used, saved, total, reg_date);
                System.out.println();
            }
        } catch(Exception exception) {
            exception.printStackTrace();
        }
    }

    public static void deleteList(){
        int num;
        try {
            System.out.println("삭제할 내역의 번호를 입력하세요.");
            System.out.print("> ");
            num = Integer.parseInt(bufferedWriter.readLine().trim());

            sql = "delete from AccountBook Where no = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, num);

            int result = preparedStatement.executeUpdate();

            if (result == 1) {
                System.out.println("삭제되었습니다.");
            } else {
                System.out.println("삭제에 실패했습니다.");
            }

        } catch(Exception exception) {
            exception.printStackTrace();
        }
    }

    public static void deleteAllList(){
        String yn;

        try {
            System.out.println("내역을 모두 삭제하시겠습니까? (y / n)");
            System.out.println("> ");

            yn = bufferedWriter.readLine().trim();

            if(yn.equals("y")){
                sql = "delete from ACCOUNTBOOK;";
                preparedStatement = connection.prepareStatement(sql);
                int result = preparedStatement.executeUpdate();
                if(result == 1){
                    System.out.println("데이터가 모두 삭제되었습니다.");
                } else {
                    System.out.println("데이터 삭제에 실패하였습니다.");
                }
            } else if(yn.equals("n")) {
                System.out.println("내역을 삭제하지 않겠습니다.");
            } else {
                System.out.println("잘못된 버튼을 누르셨습니다.");
            }

        } catch(Exception exception) {
            exception.printStackTrace();
        }
    }

}
