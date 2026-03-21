package org.example.expensemanager.service.impl;

import org.example.expensemanager.model.Revenu;
import org.example.expensemanager.repository.RevenuRepository;
import org.example.expensemanager.service.RevenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RevenueServiceImpl implements RevenuService {

  private final RevenuRepository revenuRepository;

  @Autowired
  public  RevenueServiceImpl( RevenuRepository revenuRepository){
    this.revenuRepository = revenuRepository;
  }


  @Override
  public Revenu addRevenu (Revenu revenu) {
    return revenuRepository.save(revenu);
  }

  @Override
  public Optional<Revenu> findById(Long idrevenu) {
    return revenuRepository.findById(idrevenu);
  }

  @Override
  public Revenu updateRevenu(Revenu revenu) {
    return revenuRepository.save(revenu);
  }


  @Override
  public List<Revenu> getAllRevenu() {
    return revenuRepository.findAll();
  }

  @Override
  public void deleteRevenu(Long idrevenu) {
    revenuRepository.deleteById(idrevenu);
  }
}
