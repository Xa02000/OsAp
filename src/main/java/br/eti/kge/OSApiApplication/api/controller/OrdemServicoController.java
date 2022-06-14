/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.eti.kge.OSApiApplication.api.controller;


import br.eti.kge.OSApiApplication.domain.model.OrdemServico;
import br.eti.kge.OSApiApplication.domain.repository.OrdemServicoRepository;
import br.eti.kge.OSApiApplication.domain.service.OrdemServicoService;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author devsys-a
 */
@RestController
@RequestMapping("/ordem-servico")
public class OrdemServicoController {
    
    @Autowired
    private OrdemServicoService ordemServicoService;
    
    @Autowired
    private OrdemServicoRepository ordemServicoRepository;
    
    
     @GetMapping("/ordem-servico")
    public List<OrdemServico> lista(){
        return ordemServicoRepository.findAll();
    }
    
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrdemServico criar(@RequestBody OrdemServico ordemServico){
        return ordemServicoService.criar(ordemServico);
    }
    
   
    @DeleteMapping("/{ordemServicoid}")
    public ResponseEntity<Void> excluir(@PathVariable Long ordemServicoID){
        //Verifica se cliente existe ou não
        
        if(!ordemServicoRepository.existsById(ordemServicoID)){
            return ResponseEntity.notFound().build();
        }
        
        ordemServicoService.excluir(ordemServicoID);
        return ResponseEntity.noContent().build();
    } 
    
      @GetMapping("/{ordemServicoid}")
    public ResponseEntity<OrdemServico> buscar (@PathVariable Long clienteId){
        
        Optional<OrdemServico> ordemServico = ordemServicoRepository.findById(clienteId);
        
        if (ordemServico.isPresent()){
            
            return ResponseEntity.ok(ordemServico.get());
            
        }
        
        else {
            
            return ResponseEntity.notFound().build();
            
        }
        
    }
    
         @PutMapping(""
                 + "/{ordemServicoid}")
    public ResponseEntity<OrdemServico> atualizar(@Valid @PathVariable Long ordemServicoID,
                                            @RequestBody OrdemServico ordemServico){
        
        //Verifica se o cliente existe
          if(!ordemServicoRepository.existsById(ordemServicoID)){
            return ResponseEntity.notFound().build();
        }
        
        ordemServico.setId(ordemServicoID);
        ordemServico = ordemServicoRepository.save(ordemServico);
        return ResponseEntity.ok(ordemServico);
    }
    
    }
    
    



