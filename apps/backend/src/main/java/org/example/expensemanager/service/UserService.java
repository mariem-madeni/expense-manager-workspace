package org.example.expensemanager.service;

import org.example.expensemanager.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
  User save (User user );
  Optional<User> findById(Long iduser);
  List<User> getAllUsers();
  User updateUser(User user);
  void softDeleteUser(Long iduser);
  void softDeleteUsers(List<Long> ids);
  List<User> findByIsDeletedFalse();
  List<User> findByIsDeletedTrue();
  void restoreUser(Long iduser);
  void restoreUsers(List<Long> ids);
  void deleteUserPermanently(Long iduser);
  void deleteUsersPermanently(List<Long> ids);
  User login(String email ,String password);
}
