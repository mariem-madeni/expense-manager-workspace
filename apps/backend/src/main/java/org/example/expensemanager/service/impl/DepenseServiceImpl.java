package org.example.expensemanager.service.impl;

import org.example.expensemanager.model.Depense;
import org.example.expensemanager.repository.DepenseRepository;
import org.example.expensemanager.service.DepenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepenseServiceImpl implements DepenseService {

  private final DepenseRepository depenseRepository;


  @Autowired
  public DepenseServiceImpl (DepenseRepository depenseService){
    this.depenseRepository =depenseService;
  }

  @Override
  public Depense addDepense(Depense depense) {
    return depenseRepository.save(depense);
  }

  @Override
  public Optional<Depense> findById(long iddepense) {
    return depenseRepository.findById(iddepense);
  }

  @Override
  public Depense updateDepense(Depense depense) {
    return depenseRepository.save(depense);
  }

  @Override
  public List<Depense> getAllDepense() {
    return depenseRepository.findAll();
  }

  @Override
  public void  deleteDepense(Long iddepense) {
    depenseRepository.deleteById(iddepense);
  }
}
