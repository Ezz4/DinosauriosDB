package io.emma.dinosauriosdb.repository;

import io.emma.dinosauriosdb.exception.ExcemptionDao;
import io.emma.dinosauriosdb.model.Dinosaurio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoDinosaurio implements Dao<Dinosaurio> {

    private static final String SELECT_ALL = "SELECT * FROM DINOSAURIO";
    private static final String SELECT_BY_ID = "SELECT * FROM DINOSAURIO WHERE id = ?";
    private static final String INSERT = "INSERT INTO DINOSAURIO(name, periodo, habitat) values(?, ?, ?)";
    private static final String DELETE = "DELETE FROM DINOSAURIO WHERE id = ?";
    private static final String UPDATE = "UPDATE DINOSAURIO SET name = ?, periodo = ?, habitat = ? WHERE id = ?";


    private Connection conexion;

    public DaoDinosaurio(Connection conexion) {
        this.conexion = conexion;
    }


    @Override
    public List<Dinosaurio> getAll() throws ExcemptionDao {
        List<Dinosaurio> dinosaurios = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = conexion.prepareStatement(SELECT_ALL);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Dinosaurio dinosaurio1 = new Dinosaurio(resultSet.getInt("id"), resultSet.getString("name"),
                        resultSet.getString("periodo"), resultSet.getString("habitat"));
                dinosaurios.add(dinosaurio1);
            }
        } catch (SQLException e) {
            throw new ExcemptionDao("No se encontraron registros de dinosaurios");
        } finally {
            try {
                resultSet.close();
                statement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            return dinosaurios;
        }
    }

    @Override
    public Dinosaurio get(Dinosaurio dinosaurio) throws ExcemptionDao {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Dinosaurio dinosaurio1 = new Dinosaurio();
        try {
            statement = conexion.prepareStatement(SELECT_BY_ID);
            statement.setInt(1, dinosaurio.getId());
            resultSet = statement.executeQuery();

            if(resultSet.next()) {
                dinosaurio1.setId(resultSet.getInt("id"));
                dinosaurio1.setName(resultSet.getString("name"));
                dinosaurio1.setHabitat(resultSet.getString("habitat"));
                return dinosaurio1;
            } else {
                return null;
            }
        } catch(SQLException e) {
            throw  new ExcemptionDao("No se pudo obtener el dinosaurio con ese id");
        } finally {
            try {
                resultSet.close();
                statement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    @Override
    public boolean add(Dinosaurio dinosaurio) throws ExcemptionDao {
        PreparedStatement statement = null;
        int resultSet;
        try {
            statement = conexion.prepareStatement(INSERT);
            statement.setString(1, dinosaurio.getName());
            statement.setString(2, dinosaurio.getPeriodo());
            statement.setString(3, dinosaurio.getHabitat());
            resultSet = statement.executeUpdate();
        } catch (SQLException e){
            throw new ExcemptionDao("No se pudo agregar el nuevo dinosaurio");
        }
        return resultSet == 1;
    }

    @Override
    public boolean delete(Dinosaurio dinosaurio) throws ExcemptionDao {
        PreparedStatement statement = null;
        int resultSet = 0;
        try {
            statement = conexion.prepareStatement(DELETE);
            statement.setInt(1, dinosaurio.getId());
            resultSet = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            return resultSet == 1;
        }
    }
    @Override
    public boolean update(Dinosaurio currentDinosaurio, Dinosaurio newDinosaurio) throws ExcemptionDao {
        PreparedStatement statement = null;
        int resultSet;
        Dinosaurio dinosaurioUpdate = new Dinosaurio();

        try {
            statement = conexion.prepareStatement(UPDATE);
            statement.setInt(4, currentDinosaurio.getId());
            statement.setString(1, newDinosaurio.getName());
            statement.setString(2, newDinosaurio.getPeriodo());
            statement.setString(3, newDinosaurio.getHabitat());
            resultSet = statement.executeUpdate();
        } catch (SQLException e) {
            throw new ExcemptionDao("No se pudo actualizar el dinosaurio");
        } finally {
            try {

                statement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return resultSet == 1;
    }

    @Override
    public boolean addAll(List<Dinosaurio> dinosaurios) throws ExcemptionDao {
        PreparedStatement statement = null;
        int registros = 0;
        try {

            for(Dinosaurio dinosaurio: dinosaurios){
                statement = conexion.prepareStatement(INSERT);
                statement.setString(1, dinosaurio.getName());
                statement.setString(2, dinosaurio.getPeriodo());
                statement.setString(3, dinosaurio.getHabitat());
                registros += statement.executeUpdate();
            }
        }  catch (SQLException e){
            throw new ExcemptionDao("Fallo al ingresar registro");
        }finally {
            try {
                statement.close();
            } catch (SQLException e) {
                System.out.println("fallo al cerrar el stament");
            }
        }

        return registros == dinosaurios.size();
    }
}
