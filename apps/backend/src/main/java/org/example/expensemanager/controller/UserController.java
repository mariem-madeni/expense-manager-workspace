package org.example.expensemanager.controller;

import jakarta.websocket.server.PathParam;
import lombok.Data;
import org.example.expensemanager.model.Revenu;
import org.example.expensemanager.model.User;
import org.example.expensemanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

  private final UserService userService;

  @Autowired
  public UserController(UserService userService){
    this.userService= userService;
  }

  //create user
  @PostMapping
  public ResponseEntity<User> createUser(@RequestBody User user){
    return ResponseEntity.ok(userService.save(user));
  }

  //Get User by its id
  @GetMapping("/{iduser}")
  public  ResponseEntity<User> getUser(@PathVariable Long iduser){
    return userService.findById(iduser)
      .map(ResponseEntity::ok)
      .orElse(ResponseEntity.notFound().build());
  }

  //update user profile
  @PutMapping("/{iduser}")
  public ResponseEntity<User> updateUser(@PathVariable Long iduser , @RequestBody User updateUser){
    return userService.findById(iduser)
      .map(existingUser -> {
        updateUser.setIduser(iduser);
        return ResponseEntity.ok(userService.updateUser(updateUser));

      })
      .orElse(ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{iduser}")
  public ResponseEntity<Void> softDeleteUser(@PathVariable Long iduser){
    userService.softDeleteUser(iduser);
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping("/soft-delete-users")
  public ResponseEntity<Void> softDeleteUsers(@RequestBody List<Long> ids){
    userService.softDeleteUsers(ids);
    return ResponseEntity.noContent().build();
  }

  @GetMapping("/active")
  public  ResponseEntity<List<User>> getActiveUsers(){
    return  ResponseEntity.ok(userService.findByIsDeletedFalse());
  }

  @GetMapping("/deleted")
  public ResponseEntity<List<User>> getDeletedUsers(){
    return  ResponseEntity.ok(userService.findByIsDeletedTrue());
  }
  //get all users
  @GetMapping
  public ResponseEntity<List<User>> getAllUser(){
    return ResponseEntity.ok(userService.getAllUsers());
  }

  //restoring one use
  @PutMapping("/restore/{iduser}")
  public  ResponseEntity<Void> restoreUser(@PathVariable Long iduser){
    userService.restoreUser(iduser);
    return  ResponseEntity.noContent().build();
  }

  //restore multiple users
  @PutMapping("/restore-user")
  public  ResponseEntity<Void> restoreUsers(@RequestBody List<Long> ids){
    userService.restoreUsers(ids);
    return  ResponseEntity.noContent().build();
  }

  //delete permanently
  @DeleteMapping("/permanent/{iduser}")
  public  ResponseEntity<Void> deleteUserPermanently(@PathVariable Long iduser){
    userService.deleteUserPermanently(iduser);
    return ResponseEntity.noContent().build();
  }

  //delete permanently multiple users
  @DeleteMapping("/permanent-Users")
  public  ResponseEntity<Void> deleteUsersPermanently(@RequestBody List<Long> ids){
    userService.deleteUsersPermanently(ids);
    return  ResponseEntity.noContent().build();
  }


  // login
  @PostMapping("/auth")
  public ResponseEntity<User> login(@RequestParam String email , @RequestParam String password){
    User user =userService.login(email, password);
    if(user!= null) {
      return ResponseEntity.ok(user);
    }
    else {
      return  ResponseEntity.status(401).build();
    }
    //logout
  }

}
