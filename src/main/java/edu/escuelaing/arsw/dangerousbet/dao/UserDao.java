package edu.escuelaing.arsw.dangerousbet.dao;

import edu.escuelaing.arsw.dangerousbet.model.Usuario;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;

public interface UserDao extends CrudRepository<Usuario, String> {
/*
    @Override
    public <S extends Usuario> S save(S s) {
        return null;
    }

    @Override
    public <S extends Usuario> Iterable<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<Usuario> findById(String s) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(String s) {
        return false;
    }

    @Override
    public Iterable<Usuario> findAll() {
        return null;
    }

    @Override
    public Iterable<Usuario> findAllById(Iterable<String> iterable) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(String s) {

    }

    @Override
    public void delete(Usuario usuario) {

    }

    @Override
    public void deleteAll(Iterable<? extends Usuario> iterable) {

    }

    @Override
    public void deleteAll() {

    }*/
}
