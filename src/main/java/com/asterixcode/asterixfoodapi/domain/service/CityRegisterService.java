package com.asterixcode.asterixfoodapi.domain.service;

import com.asterixcode.asterixfoodapi.domain.exception.EntityInUseException;
import com.asterixcode.asterixfoodapi.domain.exception.EntityNotFoundException;
import com.asterixcode.asterixfoodapi.domain.model.City;
import com.asterixcode.asterixfoodapi.domain.model.State;
import com.asterixcode.asterixfoodapi.domain.repository.CityRepository;
import com.asterixcode.asterixfoodapi.domain.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CityRegisterService {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private StateRepository stateRepository;

    public City save(City city){
        Long stateId = city.getState().getId();
        State state = stateRepository.getBy(stateId);

        if (state == null) {
            throw new EntityNotFoundException(
                    String.format("State with Id %d does not exist", stateId));
        }

        city.setState(state);
        return cityRepository.add(city);
    }

    public void delete(Long cityId){
        try {
            cityRepository.remove(cityId);

        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(
                    String.format("City with Id %d does not exist", cityId));

        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(
                    String.format("City with Id %d cannot be removed because it's been in use", cityId));
        }
    }
}
