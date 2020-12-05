package io.emma.dinosauriosdb.repository;

import io.emma.dinosauriosdb.exception.ExcemptionDao;

import java.util.List;

public interface Dao <Dinosaurio>{
    public List<Dinosaurio> getAll() throws ExcemptionDao;
    Dinosaurio get(Dinosaurio item) throws ExcemptionDao;
    boolean add(Dinosaurio item) throws ExcemptionDao;
    boolean delete(Dinosaurio item) throws ExcemptionDao;
    boolean update(Dinosaurio currentitem, Dinosaurio newItem) throws ExcemptionDao;
    boolean addAll(List<Dinosaurio> items) throws ExcemptionDao;
}
