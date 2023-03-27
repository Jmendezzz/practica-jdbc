package org.example.repository.impl;

import org.example.entities.Producto;
import org.example.repository.Repository;
import org.example.singleton.ConexionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoRepositoryImpl implements Repository<Producto> {
    private Connection getConnection() throws SQLException {
        return ConexionDB.getInstance();
    }
    private Producto createProduct(ResultSet resultSet) throws SQLException {
        Producto producto = new Producto();
        producto.setId(resultSet.getLong("id"));
        producto.setNombre(resultSet.getString("nombre"));
        producto.setPrecio(resultSet.getDouble("precio"));
        producto.setFechaRegistro(resultSet.getDate("fecha_registro").toLocalDate());
        return producto;
    }


    @Override
    public List<Producto> getList() {
        List<Producto>productosList=new ArrayList<>();
        try(Statement statement=getConnection().createStatement();
        ResultSet resultSet=statement.executeQuery(
            """
                SELECT  * FROM  Producto;
                """
        ))
        {
            while (resultSet.next()){
                Producto producto=createProduct(resultSet);
                productosList.add(producto);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productosList;
    }

    @Override
    public Producto getById(Long id) {
        Producto producto=null;
        try (PreparedStatement preparedStatement=getConnection()
                .prepareStatement(""" 
                                    SELECT  * FROM  Producto WHERE id=?
                                    """)
        ) {
            preparedStatement.setLong(1,id);
            ResultSet resultSet= preparedStatement.executeQuery();
            if (resultSet.next()){
                producto=createProduct(resultSet);
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return producto;
    }

    @Override
    public void save(Producto producto) {
        try(PreparedStatement preparedStatement = getConnection()
                .prepareStatement("""
                                       INSERT INTO Producto(nombre,precio,fecha_registro) values (?,?,?)
                                       """)
        ){
            preparedStatement.setString(1, producto.getNombre());
            preparedStatement.setDouble(2, producto.getPrecio());
            Date fechaRegistro = Date.valueOf(producto.getFechaRegistro());
            preparedStatement.setDate(3, fechaRegistro);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    @Override
    public void update(Producto producto) {
        try(PreparedStatement preparedStatement = getConnection()
                .prepareStatement("""
                                    UPDATE Producto SET nombre = ?, precio = ?, fecha_registro = ? WHERE id = ?;
                                      """
                )
        ){
            preparedStatement.setString(1, producto.getNombre());
            preparedStatement.setDouble(2, producto.getPrecio());
            Date fechaRegistro = Date.valueOf(producto.getFechaRegistro());
            preparedStatement.setDate(3, fechaRegistro);
            preparedStatement.setLong(4,producto.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void deleteById(Long id) {
        try(PreparedStatement preparedStatement = getConnection()
                .prepareStatement("""
                                      DELETE FROM Producto where id=?
                                      """)
        ){
            preparedStatement.setLong(1,id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }



}
