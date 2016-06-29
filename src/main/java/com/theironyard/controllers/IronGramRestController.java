package com.theironyard.controllers;

import com.theironyard.entities.Photo;
import com.theironyard.entities.User;
import com.theironyard.services.PhotoRepository;
import com.theironyard.services.UserRepository;
import com.theironyard.utils.PasswordStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * Created by hoseasandstrom on 6/28/16.
 */
@RestController
public class IronGramRestController {
    @Autowired
    UserRepository users;

    @Autowired
    PhotoRepository photos;

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public User login(@RequestBody User user, HttpSession session) throws Exception {
        User userFromDataBase = users.findFirstByName(user.getName());
        if (userFromDataBase == null) {
            user.setPassword(PasswordStorage.createHash(user.getPassword()));
            users.save(user);
        } else if (!PasswordStorage.verifyPassword(user.getPassword(), userFromDataBase.getPassword())) {
            throw new Exception ("Invalid Password");
        }

        session.setAttribute("username", user.getName());
        return user;
    }

    @RequestMapping(path = "/logout", method = RequestMethod.POST)
    public void logout(HttpSession session) {
        session.invalidate();
    }

    @RequestMapping(path = "/photos", method = RequestMethod.GET)
    public Iterable<Photo> getPhotos(HttpSession session) {
        String username = (String) session.getAttribute("username");
        User user = users.findFirstByName(username);
        return photos.findByRecipient(user);


    }
    //keep the form visible
    @RequestMapping(path = "/user", method = RequestMethod.GET)
    public User getUser(HttpSession session) {
        String username = (String) session.getAttribute("username");
        if (username == null){
            return null;
        } else {
            return users.findFirstByName(username);
        }
    }

    @RequestMapping(path = "/public-photos", method = RequestMethod.GET)
    public Iterable<Photo> publicPhotos(String username) {
        User user = users.findFirstByName(username);
        return photos.findBySenderandMakePublic(user, true);
    }
}
