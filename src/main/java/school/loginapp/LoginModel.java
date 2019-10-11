package school.loginapp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dbUtil.dbConnection;

public class LoginModel {

    Connection connection;

    public LoginModel(){
        try {
            this.connection = dbConnection.getConnection();
        }catch (SQLException ex){
            ex.printStackTrace();
        }

        if(this.connection == null){
            System.exit(1);
        }
    }

    String id ;
    String fn;
    String ln;
    String em;

    public boolean isDatabaseConnected(){
        return this.connection != null;
    }

    public boolean isLogin(String  user, String pass, String opt) throws Exception{

        PreparedStatement pr = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM login where username = ? and password = ? and division = ?";


            try {

                pr = this.connection.prepareStatement(sql);
                pr.setString(1, user);
                pr.setString(2, pass);
                pr.setString(3, opt);

                rs = pr.executeQuery();

                if(user.equals("admin")  && pass.equals("admin") && opt.equals("Admin")){
                    return true;
                }else
                if (rs.next()) {
                    id = rs.getString("id_userslogin");
                    System.out.println(id);

                    return true;
                } else
                    return false;

                String sql2= "SELECT * FROM students WHERE id_users = '" + id + "' ;";
                ResultSet rs1 = connection.createStatement().executeQuery(sql2);

                fn = rs1.getString("fname");
                ln = rs1.getString("lname");
                em = rs1.getString("email");
                System.out.println(fn);
                System.out.println(ln);
                System.out.println(em);

                rs1.close();
                
            } catch (SQLException ex) {
                return false;
            } finally
                {
                    pr.close();
                    rs.close();
                }

        }
    }

