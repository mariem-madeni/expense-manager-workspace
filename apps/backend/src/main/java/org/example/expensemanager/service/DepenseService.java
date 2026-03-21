package org.example.expensemanager.service;

import org.example.expensemanager.model.Depense;

import java.util.List;
import java.util.Optional;

public interface DepenseService {

  Depense addDepense (Depense depense);
  Optional<Depense> findById(long iddepense);
  Depense updateDepense(Depense depense);
  List<Depense> getAllDepense();
  void deleteDepense(Long iddepense);
}
