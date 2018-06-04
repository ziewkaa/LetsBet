package pl.coderslab.letsbetnow.api;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.letsbetnow.model.Event;
import pl.coderslab.letsbetnow.model.Horse;
import pl.coderslab.letsbetnow.model.User;
import pl.coderslab.letsbetnow.service.EventService;
import pl.coderslab.letsbetnow.service.HorseService;
import pl.coderslab.letsbetnow.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private HorseService horseService;

    @Autowired
    private UserService userService;

    @Autowired
    private EventService eventService;

    @ApiOperation(value = "Get all events")
    @GetMapping("/events")
    public ResponseEntity getAllEvents() {
        return ResponseEntity.ok(eventService.findAllEvents());
    }

    @ApiOperation(value = "Get all horses")
    @GetMapping("/horses")
    public ResponseEntity getHorses() {

        return ResponseEntity.ok(horseService.findAllHorses());
    }

    @ApiOperation(value = "Add user")
    @RequestMapping(value = "/user/add", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity saveUser(@RequestBody User user){
        userService.saveUser(user);
        return new ResponseEntity("User saved success", HttpStatus.OK);
    }

    @ApiOperation(value = "Update user")
    @RequestMapping(value = "/user/update/{id}", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity updateUser(@PathVariable Long id, @RequestBody User user){
        User userUpdate = userService.getUserById(id);
        userUpdate.setUsername(user.getUsername());
        userUpdate.setPassword(user.getPassword());
        userUpdate.setFirstName(user.getFirstName());
        userUpdate.setLastName(user.getLastName());
        userUpdate.setBirthDate(user.getBirthDate());
        return new ResponseEntity("User updated successfully", HttpStatus.OK);
    }



}