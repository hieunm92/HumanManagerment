package sevice;

import model.Human;
import sun.rmi.server.Dispatcher;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HumanSevice implements IHumanSevice {
    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3308/humanmanagerment",
                    "root",
                    "123456"
            );
        } catch (ClassNotFoundException e) {
            System.out.println("không có driver");
        } catch (SQLException throwables) {
            System.out.println("Không kết nối được");
        }
        System.out.println("ket noi thanh cong");
        return connection;
    }

    @Override
    public List<Human> findAll() {
        Connection connection = getConnection();
        List<Human> humanList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from human;");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                int yearOfBirth = resultSet.getInt(3);
                String address = resultSet.getString(4);

                Human human = new Human(id, name, yearOfBirth, address);
                humanList.add(human);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return humanList;
    }

    @Override
    public Human findById(int id) {
        Human human = null;
        Connection connection = getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from human where id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString(2);
                int yearOfBirth = resultSet.getInt(3);
                String address = resultSet.getString(4);

                human = new Human(id, name, yearOfBirth, address);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return human;

    }

    @Override
    public boolean remove(int id) {
        boolean test = false;
        Connection connection = getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("delete from human where id = ?");
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();

            if (preparedStatement.executeUpdate() > 0) {
                test = true;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return test;
    }

    @Override
    public boolean save(Human human) {
        boolean save = false;
        Connection connection = getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("update human set name = ?, yearOfBirth = ?,address= ? where id= ?");
            preparedStatement.setInt(4,human.getId());
            preparedStatement.setString(1,human.getName());
            preparedStatement.setInt(2,human.getYearOfBirth());
            preparedStatement.setString(3,human.getAddress());
            preparedStatement.executeUpdate();
            if(preparedStatement.executeUpdate()>0){
                save = true;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return save;
    }


}
