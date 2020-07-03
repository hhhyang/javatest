
package com.javatest.mysql;


import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MysqlMain {
    public static final String DBURL = "jdbc:mysql://localhost:3306/mldn";
    public static final String DBUSER = "root";
    public static final String DBPASS = "123456";

    public static void main(String[] args) {

        SqlConnectionManager manager = new SqlConnectionManager(DBURL, DBUSER, DBPASS);



        try {
            //String sql = "SELECT col1,col2,col3,col4 FROM iptest where col2 = ?";
            String sql = "INSERT INTO iptest (col1, col2, col3, col4) VALUES (?, ?, ?, ?)";
            PreparedStatement pstmt = manager.setPrepareStatement(sql);

            pstmt.setInt(1, 100);
            pstmt.setInt(2, 200);
            pstmt.setString(3, "hhhh");
            pstmt.setString(4, "eeeee");
            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                Object o = rs.getObject(1);
                //System.out.println(o.getClass().getName());
                System.out.println("result: " + rs.getInt(1));
                int index = ((BigInteger) o).intValue();
                System.out.println("result: " + index);
            }
            else {
                System.out.println("result: no");
            }

            /*
            pstmt.setInt(1, 30);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                Integer col1 = rs.getInt(1);
                if (col1 == null) {
                    System.out.println("wwwww");
                }
                Integer col2 = rs.getInt(2);
                String col3 = rs.getString(3);
                if (col3 == null) {
                    System.out.println("2222");
                }
                String col4 = rs.getString(4);
                System.out.println("col1: " + col1);
                System.out.println("col2: " + col2);
                System.out.println("col3: " + col3);
                System.out.println("col4: " + col4);
            }

            rs.close();

            pstmt.setInt(1, 20);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                int col1 = rs.getInt(1);
                System.out.println("col1: " + col1);
            }
            else {
                System.out.println("no");
            }
            */





        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("cat exception: " + e.getMessage());
        }
        finally {
            manager.close();
        }



    }


}

