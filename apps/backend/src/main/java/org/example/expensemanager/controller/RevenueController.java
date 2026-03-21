package org.example.expensemanager.controller;

import jakarta.websocket.server.PathParam;
import org.example.expensemanager.model.Revenu;
import org.example.expensemanager.service.RevenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/revenue")
public class RevenueController {

  private final RevenuService revenuService;

  @Autowired
  public RevenueController(RevenuService revenuService){
    this.revenuService = revenuService;
  }

  //add Income
  @PostMapping
  public ResponseEntity<Revenu> addRevenu(@RequestBody Revenu revenu){
    return  ResponseEntity.ok(revenuService.addRevenu(revenu));
  }

  // get Income  by id
  @GetMapping("/{idRevenu}")
  public ResponseEntity<Revenu> getRevenu (@PathVariable Long idRevenu){
    return revenuService.findById(idRevenu)
      .map(ResponseEntity::ok)
      .orElse(ResponseEntity.notFound().build());
  }

  //update Income
  @PutMapping("/{idRevenu}")
  public ResponseEntity<Revenu> updateRevenu(@PathVariable Long idRevenu ,@RequestBody Revenu updateRevenu){
    return revenuService.findById(idRevenu)
      .map(existingRevenu -> {
        updateRevenu.setIdrevenu(idRevenu);
        return ResponseEntity.ok(revenuService.updateRevenu(updateRevenu));
      })
      .orElse(ResponseEntity.notFound().build());
  }

  //get all Income
  @GetMapping
  public ResponseEntity<List<Revenu>> getAllRevenu(){
    return ResponseEntity.ok(revenuService.getAllRevenu());
  }

  //delete Income  by id
  @DeleteMapping("/{idRevenu}")
  public ResponseEntity<Void> deleteRevenu(@PathVariable Long idRevenu){
    revenuService.deleteRevenu(idRevenu);
    return  ResponseEntity.noContent().build();
  }


}
