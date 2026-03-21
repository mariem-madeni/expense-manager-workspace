package org.example.expensemanager.service.impl;

import org.example.expensemanager.model.User;
import org.example.expensemanager.repository.UserRepository;
import org.example.expensemanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  @Autowired
  public UserServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }


  @Override
  public User save(User user) {
    return userRepository.save(user);
  }

  @Override
  public Optional<User> findById(Long iduser) {
    return userRepository.findById(iduser);
  }


  @Override
  public List<User> getAllUsers() {
   return userRepository.findAll();
  }

  @Override
  public User updateUser(User user) {
    return userRepository.save(user);
  }

  //Soft delete is deleting the user without permanently delete from db
  @Override
  public void softDeleteUser(Long id) {
    userRepository.findById(id).ifPresent(user -> {
      user.setDeleted(true);
      userRepository.save(user);
    });
  }

  //this also delete in users but not permanently
  @Override
  public void softDeleteUsers(List<Long> ids){
    ids.forEach(this::softDeleteUser);
  }

  @Override
  public List<User> findByIsDeletedTrue(){
    return userRepository.findByIsDeletedTrue();
  }

  @Override
  public List<User> findByIsDeletedFalse(){
    return  userRepository.findByIsDeletedFalse();
  }
  //this for restore the deleted user
  @Override
  public void restoreUser(Long iduser) {
    userRepository.findById(iduser).ifPresent(user -> {
      user.setDeleted(false);
      userRepository.save(user);
    });
  }
  //this for restoring the deleted users
  @Override
  public  void restoreUsers(List<Long> ids){
    ids.forEach((this::restoreUser));
  }

  @Override
  public  void deleteUserPermanently (Long iduser){
    userRepository.deleteById(iduser);
  }

  @Override
  public void deleteUsersPermanently(List<Long> ids) {
    ids.forEach(userRepository::deleteById);
  }


  @Override
  public User login(String email, String password) {
    return  userRepository.findAll().stream()
      .filter(user -> user.getEmail().equals(email) && user.getPassword().equals(password))
      .findFirst()
      .orElse(null);
  }
}
