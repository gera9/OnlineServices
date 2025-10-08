package com.fireal99.OnlineServices.user;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public UUID create(@RequestBody User user) {
        return userService.create(user);
    }

    @GetMapping
    public ResponseEntity<List<User>> findAll(
            @RequestParam(defaultValue = "10", name = "limit") String pageSizeStr,
            @RequestParam(defaultValue = "0", name = "offset") String pageNumberStr) {

        Integer pageSize;
        Integer pageNumber;
        try {
            pageSize = Integer.parseInt(pageSizeStr);
            pageNumber = Integer.parseInt(pageNumberStr);
        } catch (NumberFormatException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(userService.findAll(pageNumber, pageSize), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable UUID id) {
        var optUsr = userService.findById(id);

        if (optUsr.isPresent()) {
            return new ResponseEntity<>(optUsr.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<User> findByUsername(@PathVariable String username) {
        var optUsr = userService.findByUsername(username);

        if (optUsr.isPresent()) {
            return new ResponseEntity<>(optUsr.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<User> findByEmail(@PathVariable String email) {
        var optUsr = userService.findByEmail(email);

        if (optUsr.isPresent()) {
            return new ResponseEntity<>(optUsr.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
