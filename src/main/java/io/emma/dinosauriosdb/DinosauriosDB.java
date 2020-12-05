package io.emma.dinosauriosdb;

import io.emma.dinosauriosdb.exception.ExcemptionDao;
import io.emma.dinosauriosdb.model.Dinosaurio;
import io.emma.dinosauriosdb.repository.DaoDinosaurio;
import io.emma.dinosauriosdb.util.DataBaseConnectionUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DinosauriosDB {
    public static void main(String[] args){

        DaoDinosaurio dinosaurioDao = null;
        Dinosaurio dinosaurioCurrent = new Dinosaurio(2);
        Dinosaurio dinosaurioNew = new Dinosaurio("Stegosaurus", "Jurasico", "Bosques y praderas");
        List<Dinosaurio> dinosaurios = new ArrayList<>();
        dinosaurios.add( new Dinosaurio("Stegosaurus", "Jurasico", "Bosques y praderas"));
        dinosaurios.add(new Dinosaurio("Stegosaurus", "Jurasico", "Bosques y praderas"));
        dinosaurios.add(new Dinosaurio("Stegosaurus", "Jurasico", "Bosques y praderas"));



        List<Dinosaurio> dinosaurioList2 = Arrays.asList(
                new Dinosaurio("Brachiosaurus", "Jurasico", "Bosques y praderas"),
                new Dinosaurio("Brachiosaurus", "Jurasico", "Bosques y praderas"),
                new Dinosaurio("Brachiosaurus", "Jurasico", "Bosques y praderas"));


        //Dinosaurio dinosaurioAdd = new Dinosaurio("dINOSAURIO", "Jurasico","Semi aridas, norte america");
        Dinosaurio dinosaurioDelete = new Dinosaurio(9);
         try {
            Connection conexion = DataBaseConnectionUtils.getConexion();
            dinosaurioDao = new DaoDinosaurio(conexion);

        } catch (SQLException e){
            System.out.println("No se pudo generar la conexion :(");
            System.exit(-1);
        }
        try {
            int numero = 1;
            //System.out.println(dinosaurioDao.get(new Dinosaurio(numero)));
            //System.out.println(dinosaurioDao.update(dinosaurioCurrent, dinosaurioNew));
            //System.out.println(dinosaurioDao.add(dinosaurioAdd));
            System.out.println(dinosaurioDao.delete(dinosaurioDelete));
            System.out.println(dinosaurioDao.getAll());
            System.out.println(dinosaurioDao.addAll(dinosaurios));
            System.out.println(dinosaurioDao.getAll());

        } catch (ExcemptionDao e) {
            System.out.println("Error al conseguir los dinosaurios");
        }
        DataBaseConnectionUtils.closeConnection();
    }
}
