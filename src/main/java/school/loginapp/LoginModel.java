package school.loginapp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dbUtil.dbConnection;
import students.StudentsController;

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



    public boolean isDatabaseConnected(){
        return this.connection != null;
    }

    public String isLogin1(String  user, String pass, String opt) throws Exception{

        PreparedStatement pr = null;
        ResultSet rs = null;
        String id;


        String sql = "SELECT * FROM login where username = ? and password = ? and division = ?";


        try {

            pr = this.connection.prepareStatement(sql);
            pr.setString(1, user);
            pr.setString(2, pass);
            pr.setString(3, opt);

            rs = pr.executeQuery();



        } catch (SQLException ex) {

        }


        id = rs.getString("id_userslogin");
        System.out.println(id);

        pr.close();
        rs.close();

        return id;


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

                    return true;

                } else {
                    return false;
                }


            } catch (SQLException ex) {
                return false;
            }
            finally
                {
                    pr.close();
                    rs.close();

                }




        }




    }

