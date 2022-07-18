package AccountBook;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;

public class AccountBook_SCV {

    static int total;
    static ResultSet resultSet = null;
    static String contents;
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
            int saved = Integer.parseInt(bufferedWriter.readLine().trim());

            sql = "SELECT  total, LAG(total) OVER (ORDER BY total) from ACCOUNTBOOK";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            int lagTotal = resultSet.getInt("total");
            int total = resultSet.getInt("total");
            if(lagTotal == 0){
                total = saved;
            } else {
                total += saved;
            }

            sql = "INSERT into ACCOUNTBOOK (no, contents, used, saved, total, reg_date) ";
            sql += " values (AccountBook_seq.nextval, ?, 0, ?, ? ,sysdate())";
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
        int used;

        try {
            //테이블에 저장할 값 입력받기
            System.out.println("사용한 내역을 입력하세요.");
            contents = bufferedWriter.readLine().trim();
            System.out.println("사용한 금액을 입력하세요.");
            used = Integer.parseInt(bufferedWriter.readLine().trim());

            sql = "insert into AccountBook(no, contents, used, saved, total, reg_date) ";
            sql += "values(AccountBook_seq.nextval, ?, 0, ?, ?, sysdate) ";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, contents);
            preparedStatement.setInt(2, used);
            preparedStatement.setInt(3,total);

            int result = preparedStatement.executeUpdate();
            resultSet = preparedStatement.executeQuery();

            total = resultSet.getInt("total") - used;
            System.out.println("잔액은 " + total + "원입니다.");

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

        } catch(Exception exception) {
            exception.printStackTrace();
        }
    }

    public static void deleteList(){
        String num;
        try {
            System.out.println("삭제할 내역의 번호를 입력하세요.");
            num = bufferedWriter.readLine().trim();
        } catch(Exception exception) {
            exception.printStackTrace();
        }
    }

    public static void deleteAllList(){
        try {

        } catch(Exception exception) {
            exception.printStackTrace();
        }
    }

}
