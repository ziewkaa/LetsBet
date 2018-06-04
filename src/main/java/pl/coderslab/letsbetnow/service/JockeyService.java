package pl.coderslab.letsbetnow.service;

import pl.coderslab.letsbetnow.model.Jockey;

import java.util.List;

public interface JockeyService {

    void saveJockey(Jockey jockey);

    List<Jockey> findAllJockeys();

    Jockey findJockeyById(Long id);

}
