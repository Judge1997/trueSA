package database;

import models.*;
import models.Package;
import tools.GenTime;

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

    public List<Customer> loadSearchName(String str) throws ClassNotFoundException, SQLException {
        List<Customer> list = new Vector<>();

        Class.forName("org.sqlite.JDBC");
        String dbURL = "jdbc:sqlite:customerDB.db";
        Connection connection = DriverManager.getConnection(dbURL);
        if(connection != null){
            System.out.println("Connected to customerDB.db");
            String query = "Select * from customer WHERE name LIKE '%"+str+"%'";
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

                list.add(new Customer(id,name,aeName,region,locationInstall,businessID,capital,province,khet,khwang,employee,contaceTelNum,contactFax,contact,contactName,
                        packetCost));
            }
            connection.close();
            System.out.println("Closed to customerDB.db");
        } else {
            System.out.println("Error to open customerDB.db");
        }

        return list;
    }

    public List<String> loadNameEmployee() throws ClassNotFoundException, SQLException {
        List<String> list = new Vector<>();

        Class.forName("org.sqlite.JDBC");
        String dbURL = "jdbc:sqlite:customerDB.db";
        Connection connection = DriverManager.getConnection(dbURL);
        if(connection != null){
            System.out.println("Connected to customerDB.db");
            String query = "Select * from user WHERE position == '"+"CustomerManager"+"'";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String name = resultSet.getString(1);
                list.add(name);
            }
            connection.close();
            System.out.println("Closed to customerDB.db");
        } else {
            System.out.println("Error to open customerDB.db");
        }

        return list;
    }

    public String checkUserAndPassword(String i, String p, int login) throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        String dbURL = "jdbc:sqlite:customerDB.db";
        Connection connection = DriverManager.getConnection(dbURL);
        if(connection != null){
            System.out.println("Connected to customerDB.db");
            String pos;
            if (login == 1){
                pos = "CustomerManager";
            } else {
                pos = "PackageManager";
            }
            String query = "Select * from user WHERE position == '"+pos+"'";
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

    public List<Package> loadPackageDB() throws ClassNotFoundException, SQLException {
        List<Package> packages = new Vector<Package>();

        Class.forName("org.sqlite.JDBC");
        String dbURL = "jdbc:sqlite:customerDB.db";
        Connection connection = DriverManager.getConnection(dbURL);
        if(connection != null){
            System.out.println("Connected to customerDB.db");
            String query = "Select * from package WHERE status == 'Active'";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                double price = resultSet.getDouble(3);
                double net = resultSet.getDouble(4);
                int voice = resultSet.getInt(5);
                double data = resultSet.getDouble(6);
                int mobileQuantity = resultSet.getInt(7);
                double mobileSpeed = resultSet.getDouble(8);
                int mobileTimes = resultSet.getInt(9);
                int tvs = resultSet.getInt(10);
                String status = resultSet.getString(11);

                packages.add(new Package(id, name, price, net, voice, data, mobileQuantity, mobileSpeed, mobileTimes, tvs, status));
            }
            connection.close();
            System.out.println("Closed to customerDB.db");
        } else {
            System.out.println("Error to open customerDB.db");
        }
        return packages;
    }

    public void writePacketDB(Package p) throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        String dbURL = "jdbc:sqlite:customerDB.db";
        Connection connection = DriverManager.getConnection(dbURL);
        if (connection != null) {
            System.out.println("Connected to customerDB.db");
            String query = "INSERT INTO package (name, price, net, voice, data, mobileQuantity, mobileSpeed, mobileTimes, tvs, status) "+"VALUES ( " +
                    "'"+p.getName()+"',"+p.getPrice()+","+p.getNet()+","+p.getVoice()+","+p.getData()+","+p.getMobileQuantity()+","+p.getMobileSpeed()+","+p.getMobileTimes()+","+p.getTvs()+", '"+p.getStatus()+"'"+");";
            System.out.println(query);
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            connection.close();
            System.out.println("Closed to customerDB.db");
        } else {
            System.out.println("Error to open customerDB.db");
        }
    }

    public void inActivePackageDB(int id) throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        String dbURL = "jdbc:sqlite:customerDB.db";
        Connection connection = DriverManager.getConnection(dbURL);
        if (connection != null) {
            System.out.println("Connected to customerDB.db");
            String query = "UPDATE package SET status = 'InActive' WHERE id = "+id+";";
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
            String query = "Select * from requirement";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                int idCustomer = resultSet.getInt(2);
                String detail = resultSet.getString(3);
                String date = resultSet.getString(4);

                requirements.add(new Requirement(id, idCustomer, detail, date));
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
            String query = "INSERT INTO requirement (idCustomer, detail, date) "+"VALUES ( " +requirement.getIdCustomer()+
                    ",'"+requirement.getDetail()+"','"+requirement.getDate()+"');";
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            connection.close();
            System.out.println("Closed to customerDB.db");
        } else {
            System.out.println("Error to open customerDB.db");
        }

    }

    public void writeCustomerRequirementDB(int idCustomer, int idRequirement) throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        String dbURL = "jdbc:sqlite:customerDB.db";
        Connection connection = DriverManager.getConnection(dbURL);
        if (connection != null) {
            System.out.println("Connected to customerDB.db");
            String query = "INSERT INTO customerRequirement (idCustomer, idRequirement) "+"VALUES ( "+idCustomer+","+idRequirement+");";
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            connection.close();
            System.out.println("Closed to customerDB.db");
        } else {
            System.out.println("Error to open customerDB.db");
        }
    }

    public List<Requirement> loadCustomerRequirement(int idC) throws ClassNotFoundException, SQLException {
        List<Requirement> customerRequirements = new Vector<>();

        Class.forName("org.sqlite.JDBC");
        String dbURL = "jdbc:sqlite:customerDB.db";
        Connection connection = DriverManager.getConnection(dbURL);
        if (connection != null) {
            System.out.println("Connected to customerDB.db");
            String query = "SELECT * FROM requirement WHERE idCustomer =="+idC;
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                int idCustomer = resultSet.getInt(2);
                String detail = resultSet.getString(3);
                String date = resultSet.getString(4);

                customerRequirements.add(new Requirement(id, idCustomer, detail, date));
            }
            connection.close();
            System.out.println("Closed to customerDB.db");
        } else {
            System.out.println("Error to open customerDB.db");
        }

        return customerRequirements;
    }

    public void deleteRequirementDB(int id) throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        String dbURL = "jdbc:sqlite:customerDB.db";
        Connection connection = DriverManager.getConnection(dbURL);
        if (connection != null) {
            System.out.println("Connected to customerDB.db");
            String query = "DELETE FROM requirement WHERE id = "+id;
            Statement statement = connection.createStatement();
            statement.executeQuery(query);
            connection.close();
            System.out.println("Closed to customerDB.db");
        } else {
            System.out.println("Error to open customerDB.db");
        }
    }

    public List<CustomerPackage> loadAllCustomerPackage() throws SQLException, ClassNotFoundException {
        List<CustomerPackage> packages = new Vector<CustomerPackage>();

        Class.forName("org.sqlite.JDBC");
        String dbURL = "jdbc:sqlite:customerDB.db";
        Connection connection = DriverManager.getConnection(dbURL);
        if (connection != null) {
            System.out.println("Connected to customerDB.db");
            String query = "SELECT * from customerPackage";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                int idCustomer = resultSet.getInt(2);
                int idPackage = resultSet.getInt(3);
                String status = resultSet.getString(4);
                String startTime = resultSet.getString(5);
                String endTime = resultSet.getString(6);

                packages.add(new CustomerPackage(id, idCustomer, idPackage, status, startTime, endTime));
            }

            connection.close();
            System.out.println("Closed to customerDB.db");
        } else {
            System.out.println("Error to open customerDB.db");
        }

        return packages;
    }

    public List<CustomerPackage> loadCustomerPackage(int idC) throws SQLException, ClassNotFoundException {
        List<CustomerPackage> packages = new Vector<CustomerPackage>();

        Class.forName("org.sqlite.JDBC");
        String dbURL = "jdbc:sqlite:customerDB.db";
        Connection connection = DriverManager.getConnection(dbURL);
        if (connection != null) {
            System.out.println("Connected to customerDB.db");
            String query = "SELECT * from customerPackage WHERE idCustomer = "+idC+";";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                int idCustomer = resultSet.getInt(2);
                int idPackage = resultSet.getInt(3);
                String status = resultSet.getString(4);
                String startTime = resultSet.getString(5);
                String endTime = resultSet.getString(6);

                packages.add(new CustomerPackage(id, idCustomer, idPackage, status, startTime, endTime));
            }

            connection.close();
            System.out.println("Closed to customerDB.db");
        } else {
            System.out.println("Error to open customerDB.db");
        }

        return packages;
    }

    public int checkCustomerPackage(int idP) throws SQLException, ClassNotFoundException {
        int count = 0;

        Class.forName("org.sqlite.JDBC");
        String dbURL = "jdbc:sqlite:customerDB.db";
        Connection connection = DriverManager.getConnection(dbURL);
        if (connection != null) {
            System.out.println("Connected to customerDB.db");
            String query = "SELECT * from customerPackage WHERE idPackage = "+idP+";";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                count++;
            }
            connection.close();
            System.out.println("Closed to customerDB.db");
        } else {
            System.out.println("Error to open customerDB.db");
        }
        return count;
    }

    public List<PackageForCustomer> loadPackageFromCustomerPackage(int idC) throws SQLException, ClassNotFoundException {
        List<PackageForCustomer> packages = new Vector<>();
        Class.forName("org.sqlite.JDBC");
        String dbURL = "jdbc:sqlite:customerDB.db";
        Connection connection = DriverManager.getConnection(dbURL);
        if (connection != null) {
            System.out.println("Connected to customerDB.db");
            String query = "Select * from customerPackage JOIN package ON package.id=customerPackage.idPackage WHERE customerPackage.idCustomer="+idC;
            System.out.println(query);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt(7);
                String name = resultSet.getString(8);
                double price = resultSet.getDouble(9);
                int net = resultSet.getInt(10);
                int voice = resultSet.getInt(11);
                int data = resultSet.getInt(12);
                int mobileQuantity = resultSet.getInt(13);
                int mobileSpeed = resultSet.getInt(14);
                int mobileTimes = resultSet.getInt(15);
                int tvs = resultSet.getInt(16);
                String status = resultSet.getString(4);
                String startTime = resultSet.getString(5);
                String endTime = resultSet.getString(6);
                packages.add(new PackageForCustomer(id, name, price, net, voice, data, mobileQuantity, mobileSpeed, mobileTimes, tvs, status, startTime, endTime));
            }
            connection.close();
            System.out.println("Closed to customerDB.db");
        } else {
            System.out.println("Error to open customerDB.db");
        }

        return packages;
    }

    public List<PackageForCustomer> loadPackageFromCustomerPackage(List<CustomerPackage> customerPackages) throws ClassNotFoundException, SQLException {
        List<PackageForCustomer> packages = new Vector<>();

        Class.forName("org.sqlite.JDBC");
        String dbURL = "jdbc:sqlite:customerDB.db";
        Connection connection = DriverManager.getConnection(dbURL);
        if (connection != null) {
            System.out.println("Connected to customerDB.db");
            String query = "Select * from package";
            Statement statement = connection.createStatement();

            for (CustomerPackage i : customerPackages){
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    int id = resultSet.getInt(1);
                    if (i.getIdPackage() == id){
                        String name = resultSet.getString(2);
                        double price = resultSet.getDouble(3);
                        double net = resultSet.getDouble(4);
                        int voice = resultSet.getInt(5);
                        double data = resultSet.getDouble(6);
                        int mobileQuantity = resultSet.getInt(7);
                        double mobileSpeed = resultSet.getDouble(8);
                        int mobileTimes = resultSet.getInt(9);
                        int tvs = resultSet.getInt(10);
                        String status = i.getStatus();
                        String startTime = i.getStartTime();
                        String endTime = i.getEndTime();
                        packages.add(new PackageForCustomer(id, name, price, net, voice, data, mobileQuantity, mobileSpeed, mobileTimes, tvs, status, startTime, endTime));
                    }
                }
            }
            connection.close();
            System.out.println("Closed to customerDB.db");
        } else {
            System.out.println("Error to open customerDB.db");
        }

        return packages;
    }

    public void writeCustomerPackage(int idCustomer, int idPackage) throws SQLException, ClassNotFoundException {
        GenTime time = new GenTime();
        String startTime = time.getTime();
        String endTime = time.getTimePlusOneYear(startTime);
        Class.forName("org.sqlite.JDBC");
        String dbURL = "jdbc:sqlite:customerDB.db";
        Connection connection = DriverManager.getConnection(dbURL);
        if (connection != null) {
            System.out.println("Connected to customerDB.db");
            String query = "INSERT INTO customerPackage (idCustomer, idPackage, status, startTime, endTime) "+"VALUES ( " +
                    ""+idCustomer+","+idPackage+", 'Active','"+startTime+"','"+endTime+"');";

            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            connection.close();
            System.out.println("Closed to customerDB.db");
        } else {
            System.out.println("Error to open customerDB.db");
        }
    }

    public void deleteCustomerPackage(int id) throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        String dbURL = "jdbc:sqlite:customerDB.db";
        Connection connection = DriverManager.getConnection(dbURL);
        if (connection != null) {
            System.out.println("Connected to customerDB.db");
            String query = "DELETE FROM customerPackage WHERE id = "+id+";";
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            connection.close();
            System.out.println("Closed to customerDB.db");
        } else {
            System.out.println("Error to open customerDB.db");
        }
    }

    public void inActiveCustomerPackage(int id) throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        String dbURL = "jdbc:sqlite:customerDB.db";
        Connection connection = DriverManager.getConnection(dbURL);
        if (connection != null) {
            System.out.println("Connected to customerDB.db");
            String query = "UPDATE customerPackage SET status = 'InActive' WHERE id = "+id+";";
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            connection.close();
            System.out.println("Closed to customerDB.db");
        } else {
            System.out.println("Error to open customerDB.db");
        }
    }

    public List<Integer> loadTvDB() throws SQLException, ClassNotFoundException {
        List<Integer> list = new Vector<>();

        Class.forName("org.sqlite.JDBC");
        String dbURL = "jdbc:sqlite:customerDB.db";
        Connection connection = DriverManager.getConnection(dbURL);
        if (connection != null) {
            System.out.println("Connected to customerDB.db");
            String query = "SELECT * FROM tv";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while(resultSet.next()){
                list.add(resultSet.getInt(2));
            }

            connection.close();
            System.out.println("Closed to customerDB.db");
        } else {
            System.out.println("Error to open customerDB.db");
        }

        return list;
    }
}
