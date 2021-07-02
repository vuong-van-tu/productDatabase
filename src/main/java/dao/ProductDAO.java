package dao;

import model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO implements IProductDAO{
    private String jdbcUrl ="jdbc:mysql://localhost:3306/product?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "123456";
    private static final String INSERT_PRODUCT_SQL ="INSERT INTO product"+"(name,price,description,nsx)VALUES"+"(?,?,?,?);";
    private static final String SELECT_PRODUCT_BY_ID =  "select * from product where id = ?";
    private static final String SELECT_PRODUCT_ALL = "select * from product";
    private static final String DELETE_PRODUCT_SQL ="DELETE FROM product where id= ?";
    private static final String UPDATE_PRODUCT_SQL ="UPDATE product set name = ?,price=?,description=?,nsx=? where id = ?";
    private static final String SELECT_PRODUCT_BY_NAME="select * from product where name like ?";
    private static final String SORT_BY_PRICE = "select * from product order by product.price asc";
    public ProductDAO(){};
    protected Connection getConnection(){
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcUrl,jdbcUsername,jdbcPassword);
        } catch (  SQLException e) {
            printSQLException(e);
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        return connection;
    }

    @Override
    public void insertProduct(Product product) throws SQLException {
        System.out.println(INSERT_PRODUCT_SQL);
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PRODUCT_SQL)){
            preparedStatement.setString(1,product.getName());
            preparedStatement.setFloat(2,product.getPrice());
            preparedStatement.setString(3,product.getDescription());
            preparedStatement.setString(4,product.getNsx());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        }catch(SQLException e){
            printSQLException(e);
        }
    }

    @Override
    public Product selectProduct(int id) {
        Product product = null;
        try (Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUCT_BY_ID)) {
            preparedStatement.setInt(1,id);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                String name = rs.getString("name");
                Float price = rs.getFloat("price");
                String description = rs.getString("description");
                String nsx = rs.getString("nsx");
                product = new Product(id,name, price, description, nsx);
            }
        } catch (SQLException e) {
           printSQLException(e);
        }
        return product;
    }

    @Override
    public List<Product> selectAllProducts() {
        List<Product> products = new ArrayList<>();
            try (Connection connection = getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUCT_ALL)) {
                System.out.println(preparedStatement);
                ResultSet rs = preparedStatement.executeQuery();
                while (rs.next()){
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    Float price = rs.getFloat("price");
                    String description = rs.getString("description");
                    String nsx = rs.getString("nsx");
                    products.add(new Product(id, name, price, description, nsx));
                }
            } catch (SQLException e) {
                printSQLException(e);
            }
        return products;
    }

    @Override
    public boolean deleteProduct(int id) throws SQLException {
        boolean rowDelete;
        try (Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement(DELETE_PRODUCT_SQL)){
            statement.setInt(1,id);
            rowDelete = statement.executeUpdate()>0;
        }
        return rowDelete;
    }

    @Override
    public boolean updateProduct(Product product) throws SQLException {
        boolean rowUpdate;
        try (Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement(UPDATE_PRODUCT_SQL)){
            statement.setString(1,product.getName());
            statement.setFloat(2,product.getPrice());
            statement.setString(3,product.getDescription());
            statement.setString(4,product.getNsx());
            statement.setInt(5,product.getId());

            rowUpdate = statement.executeUpdate()>0;
        }
        return rowUpdate;
    }

    @Override
    public List<Product> selectProduct(String findname) {
        List<Product> products = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUCT_BY_NAME)) {
            preparedStatement.setString(1,"%"+findname+"%");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                int id = rs.getInt("id");
                Float price = rs.getFloat("price");
                String description = rs.getString("description");
                String name = rs.getString("name");
                String nsx = rs.getString("nsx");
                products.add(new Product(id,name, price, description, nsx));

            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return products;
    }

    @Override
    public List<Product> sortByPrice() {
        List<Product> products = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SORT_BY_PRICE)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                Float price = rs.getFloat("price");
                String description = rs.getString("description");
                String nsx = rs.getString("nsx");
                products.add(new Product(id, name, price, description, nsx));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return products;
    }


    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }

}

