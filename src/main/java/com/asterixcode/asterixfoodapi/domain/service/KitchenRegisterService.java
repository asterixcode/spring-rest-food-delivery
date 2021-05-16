package com.asterixcode.asterixfoodapi.domain.service;

import com.asterixcode.asterixfoodapi.domain.exception.EntityInUseException;
import com.asterixcode.asterixfoodapi.domain.exception.EntityNotFoundException;
import com.asterixcode.asterixfoodapi.domain.model.Kitchen;
import com.asterixcode.asterixfoodapi.domain.repository.KitchenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

// A Service class is Business Logic, so it can't return a ResponseEntity, for example
// A Service cannot have access to classes from the API implementation -> Service class has no knowledge about the HTTP protocol
@Service
public class KitchenRegisterService {

    @Autowired
    private KitchenRepository kitchenRepository;

    public Kitchen save(Kitchen kitchen) {
        return kitchenRepository.add(kitchen);
    }

    public void delete(Long kitchenId){
        try {
            kitchenRepository.remove(kitchenId);

        } catch (EmptyResultDataAccessException e){
            throw new EntityNotFoundException(
                    String.format("Kitchen with Id %d does not exist in the database", kitchenId));
        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(
                    String.format("Kitchen with Id %d cannot be removed because it's been in use", kitchenId));
            // this new Exception is a "translation" of the DataIntegrityViolationException.
            // As Service classes don't know about about any implementation,
            // we create our own and new Exception.
            // the new Exception is to handle when the kitchen is in use by a restaurant
            // the name of this Exception is related/say something about the business
            // in this case, it says that this entity (kitchen) is in use.
            // We then create our Business's Exceptions.
        }
    }
}
