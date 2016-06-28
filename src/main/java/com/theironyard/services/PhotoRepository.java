package com.theironyard.services;

import com.theironyard.entities.Photo;
import com.theironyard.entities.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by hoseasandstrom on 6/28/16.
 */
public interface PhotoRepository extends CrudRepository<Photo, Integer> {

}