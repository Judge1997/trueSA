package database;

import models.Customer;

import java.sql.*;
import java.util.List;
import java.util.Vector;

public class CustomerDB {

    private static CustomerDB customerDB = null;

    private CustomerDB(){};

    public static CustomerDB getSelf(){
        if (customerDB == null){
            return new CustomerDB();
        }
        return customerDB;
    }

    public List<Customer> loadCustonerDB() throws SQLException, ClassNotFoundException, SQLException {
        List<Customer> customers = new Vector<Customer>();

        Class.forName("org.sqlite.JDBC");
        String dbURL = "jdbc:sqlite:customerDB.db";
        Connection connection = DriverManager.getConnection(dbURL);
        if(connection != null){
            System.out.println("Connected to customerDB.db");
            String query = "Select * from customer";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String aeName = resultSet.getString(3);
                String region = resultSet.getString(4);
                String locationInstall = resultSet.getString(5);
                String businessID = resultSet.getString(6);
                String capital = resultSet.getString(7);
                String province = resultSet.getString(8);
                String khet = resultSet.getString(9);
                String khwang = resultSet.getString(10);
                String employee = resultSet.getString(11);
                String contaceTelNum = resultSet.getString(12);
                String contactFax = resultSet.getString(13);
                String contact = resultSet.getString(14);
                String contactName = resultSet.getString(15);
                double packetCost = resultSet.getDouble(16);

                customers.add(new Customer(id,name,aeName,region,locationInstall,businessID,capital,province,khet,khwang,employee,contaceTelNum,contactFax,contact,contactName,
                        packetCost));
            }
            System.out.println("Closed to customerDB.db");
        } else {
            System.out.println("Error to open customerDB.db");
        }
        return customers;
    }

    public void writeCustonerDB(Customer customer) throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        String dbURL = "jdbc:sqlite:customerDB.db";
        Connection connection = DriverManager.getConnection(dbURL);
        if (connection != null) {
            System.out.println("Connected to customerDB.db");
            String query = "INSERT INTO customer (name, aeName, region, locationInstall, businessId, capital, province, khet, khwang, employee, contactTelNum, contactFax, contact, contactName, packetCost) "+"VALUES ( " +
                    "'"+customer.getName()+"','"+customer.getAeName()+"','"+customer.getRegion()+"','"+customer.getLocationInstall()+"','"+customer.getBusinessId()+"','"+customer.getCapital()+"','"+customer.getProvince()+
                    "','"+customer.getKhet()+"','"+customer.getKhwang()+"','"+customer.getEmployee()+"','"+customer.getContactTelNum()+"','"+customer.getContactFax()+"','"+customer.getContact()+"','"+customer.getContactName()+
                    "',"+customer.getPacketCost()+");";
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            connection.close();
            System.out.println("Closed to customerDB.db");
        } else {
            System.out.println("Error to open customerDB.db");
        }
    }

    public void deleteCustonerDB(int id) throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        String dbURL = "jdbc:sqlite:customerDB.db";
        Connection connection = DriverManager.getConnection(dbURL);
        if (connection != null) {
            System.out.println("Connected to customerDB.db");
            String query = "DELETE FROM customer WHERE id = "+id+";";
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            connection.close();
            System.out.println("Closed to customerDB.db");
        } else {
            System.out.println("Error to open customerDB.db");
        }
    }
}
