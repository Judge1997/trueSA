package database;

import models.Customer;
import models.Package;
import models.Requirement;

import java.sql.*;
import java.util.List;
import java.util.Vector;

public class DBConnector {

    private static DBConnector customerDB = null;

    private DBConnector(){};

    public static DBConnector getSelf(){
        if (customerDB == null){
            return new DBConnector();
        }
        return customerDB;
    }

    public String checkUserAndPassword(String i, String p) throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        String dbURL = "jdbc:sqlite:customerDB.db";
        Connection connection = DriverManager.getConnection(dbURL);
        if(connection != null){
            System.out.println("Connected to customerDB.db");
            String query = "Select * from user";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String name = resultSet.getString(1);
                String id = resultSet.getString(2);
                String password = resultSet.getString(3);
                String position = resultSet.getString(4);

                if (i.equals(id) && p.equals(password)){
                    return name+" "+position;
                }
            }
            connection.close();
            System.out.println("Closed to customerDB.db");
        } else {
            System.out.println("Error to open customerDB.db");
        }
        return "Id or password is wrong";
    }

    public List<Customer> loadCustonerDB() throws ClassNotFoundException, SQLException {
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
            connection.close();
            System.out.println("Closed to customerDB.db");
        } else {
            System.out.println("Error to open customerDB.db");
        }
        return customers;
    }

    public void writeCustomerDB(Customer customer) throws ClassNotFoundException, SQLException {
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

    public void editCustomerDB(Customer customer) throws ClassNotFoundException, SQLException{
        Class.forName("org.sqlite.JDBC");
        String dbURL = "jdbc:sqlite:customerDB.db";
        Connection connection = DriverManager.getConnection(dbURL);
        if (connection != null) {
            System.out.println("Connected to customerDB.db");
            String query = "UPDATE customer SET name = '"+customer.getName()+"' , aeName = '"+customer.getAeName()+"' , region ='"+customer.getRegion()+"' , locationInstall ='"+customer.getLocationInstall()
                    +"' , businessId ='"+customer.getBusinessId()+"' , capital ='"+customer.getCapital()+"' , province ='"+customer.getProvince()+"' , khet ='"+customer.getKhet()
                    +"' , khwang ='"+customer.getKhwang()+"' , employee ='"+customer.getEmployee()+"' , contactTelNum ='"+customer.getContactTelNum()+"' , contactFax ='"+customer.getContactFax()
                    +"' , contact ='"+customer.getContact()+"' , contactName ='"+customer.getContactName()+"' , packetCost ='"+customer.getPacketCost()
                    +"' WHERE id = "+customer.getId();
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            connection.close();
            System.out.println("Closed to customerDB.db");
        } else {
            System.out.println("Error to open customerDB.db");
        }
    }

    public void deleteCustomerDB(int id) throws ClassNotFoundException, SQLException {
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

    public List<Package> loadPacketDB() throws ClassNotFoundException, SQLException {
        List<Package> packets = new Vector<Package>();

        Class.forName("org.sqlite.JDBC");
        String dbURL = "jdbc:sqlite:customerDB.db";
        Connection connection = DriverManager.getConnection(dbURL);
        if(connection != null){
            System.out.println("Connected to customerDB.db");
            String query = "Select * from package";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                double price = resultSet.getDouble(3);
                String net = resultSet.getString(4);
                String voice = resultSet.getString(5);
                String data = resultSet.getString(6);
                String mobile = resultSet.getString(7);
                String tvs = resultSet.getString(8);

                packets.add(new Package(id, name, price, net, voice, data, mobile, tvs));
            }
            connection.close();
            System.out.println("Closed to customerDB.db");
        } else {
            System.out.println("Error to open customerDB.db");
        }
        return packets;
    }

    public void writePacketDB(Package p) throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        String dbURL = "jdbc:sqlite:customerDB.db";
        Connection connection = DriverManager.getConnection(dbURL);
        if (connection != null) {
            System.out.println("Connected to customerDB.db");
            String query = "INSERT INTO package (name, price, net, voice, data, mobile, tvs) "+"VALUES ( " +
                    "'"+p.getName()+"',"+p.getPrice()+",'"+p.getNet()+"','"+p.getVoice()+"','"+p.getData()+"','"+p.getMoblie()+"','"+p.getTvs()+"');";
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            connection.close();
            System.out.println("Closed to customerDB.db");
        } else {
            System.out.println("Error to open customerDB.db");
        }
    }

    public void deletePacketDB(int id) throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        String dbURL = "jdbc:sqlite:customerDB.db";
        Connection connection = DriverManager.getConnection(dbURL);
        if (connection != null) {
            System.out.println("Connected to customerDB.db");
            String query = "DELETE FROM package WHERE id = "+id+";";
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            connection.close();
            System.out.println("Closed to customerDB.db");
        } else {
            System.out.println("Error to open customerDB.db");
        }
    }

    public List<Requirement> loadRequirementDB() throws ClassNotFoundException, SQLException {
        List<Requirement> requirements = new Vector<Requirement>();

        Class.forName("org.sqlite.JDBC");
        String dbURL = "jdbc:sqlite:customerDB.db";
        Connection connection = DriverManager.getConnection(dbURL);
        if(connection != null){
            System.out.println("Connected to customerDB.db");
            String query = "Select * from packet";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String net = resultSet.getString(4);
                String voice = resultSet.getString(5);
                String data = resultSet.getString(6);
                String mobile = resultSet.getString(7);
                String tvs = resultSet.getString(8);

                requirements.add(new Requirement(id, net, voice, data, mobile, tvs));
            }
            connection.close();
            System.out.println("Closed to customerDB.db");
        } else {
            System.out.println("Error to open customerDB.db");
        }
        return requirements;
    }

    public void writeRequirementDB(Requirement requirement) throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        String dbURL = "jdbc:sqlite:customerDB.db";
        Connection connection = DriverManager.getConnection(dbURL);
        if (connection != null) {
            System.out.println("Connected to customerDB.db");
            String query = "INSERT INTO requirement (net, voice, data, mobile, tvs) "+"VALUES ( " +
                    "'"+requirement.getNet()+"','"+requirement.getVoice()+"','"+requirement.getData()+"','"+requirement.getMoblie()+"','"+requirement.getTvs()+");";
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            connection.close();
            System.out.println("Closed to customerDB.db");
        } else {
            System.out.println("Error to open customerDB.db");
        }
    }

    public void deleteRequirementDB(int id) throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        String dbURL = "jdbc:sqlite:customerDB.db";
        Connection connection = DriverManager.getConnection(dbURL);
        if (connection != null) {
            System.out.println("Connected to customerDB.db");
            String query = "DELETE FROM requirement WHERE id = "+id+";";
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            connection.close();
            System.out.println("Closed to customerDB.db");
        } else {
            System.out.println("Error to open customerDB.db");
        }
    }
    
}
