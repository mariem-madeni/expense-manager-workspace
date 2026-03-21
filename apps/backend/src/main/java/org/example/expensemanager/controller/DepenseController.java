package org.example.expensemanager.controller;

import org.example.expensemanager.service.DepenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/depense")
public class DepenseController {
  private final DepenseService depenseService;

  @Autowired
  public DepenseController (DepenseService depenseService){
    this.depenseService =depenseService;
  }


  // add Expense


}
