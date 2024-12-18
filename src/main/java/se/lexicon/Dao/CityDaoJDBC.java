package se.lexicon.Dao;

import se.lexicon.City;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static se.lexicon.db.MySQLConnection.getConnection;

public class CityDaoJDBC implements CityDao {

    @Override
    public City findById(int id) {
        String query = "SELECT * FROM city WHERE id = ?";
        try (
                Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(query))
        {
            statement.setInt(1, id);
            try (
                    ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()){
                    return new City(
                            resultSet.getInt("ID"),
                            resultSet.getString("Name"),
                            resultSet.getString("CountryCode"),
                            resultSet.getString("District"),
                            resultSet.getInt("Population")
                    );
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public List<City> findByCode(String code) {
        String query = "SELECT * FROM city WHERE CountryCode = ?";
        List<City> cityList = new ArrayList<>();
        try ( Connection connection = getConnection();
              PreparedStatement statement = connection.prepareStatement(query)
        ) {
            statement.setString(1, code);
            try (ResultSet resultSet = statement.executeQuery())
            { while (resultSet.next()) {
                cityList.add(new City(
                        resultSet.getInt("ID"),
                        resultSet.getString("Name"),
                        resultSet.getString("CountryCode"),
                        resultSet.getString("District"),
                        resultSet.getInt("Population") ));
            }

            }
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        return cityList;
    }


    @Override
    public List<City> findByName(String name) {
        String query = "SELECT * FROM city WHERE Name LIKE ?";
        List<City> cityList = new ArrayList<>();
        try ( Connection connection = getConnection();
              PreparedStatement statement = connection.prepareStatement(query)
        ) {
            statement.setString(1, name);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    cityList.add(new City(
                            resultSet.getInt("ID"),
                            resultSet.getString("Name"),
                            resultSet.getString("CountryCode"),
                            resultSet.getString("District"),
                            resultSet.getInt("Population")
                    ));
                }

            }
        } catch (SQLException ex) {
            ex.printStackTrace();;
        }

        return List.of();
    }

    @Override
    public List<City> findAll() {
        String query = "SELECT * FROM city";
        List<City> cityList = new ArrayList<>();
        try (
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query)
        ) {
            while (resultSet.next()) {
                cityList.add(new City(
                        resultSet.getInt("ID"),
                        resultSet.getString("Name"),
                        resultSet.getString("CountryCode"),
                        resultSet.getString("District"),
                        resultSet.getInt("Population")
                ));

            }
        }catch (SQLException ex) {
            ex.printStackTrace();
        }
        return cityList;
    }

    @Override
    public City add(City city) {
        return null;
    }

    @Override
    public City update(City city) {
        return null;
    }

    @Override
    public int delete(City city) {
        return 0;
    }




}
