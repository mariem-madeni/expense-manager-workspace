package org.example.expensemanager.service;

import org.example.expensemanager.model.Revenu;

import java.util.List;
import java.util.Optional;

public interface RevenuService {

  Revenu  addRevenu(Revenu revenu);
  Optional<Revenu> findById(Long idrevenu);
  Revenu updateRevenu ( Revenu revenu);
  List <Revenu> getAllRevenu();
  void deleteRevenu (Long idrevenu );
}
