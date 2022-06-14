/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.eti.kge.OSApiApplication.domain.service;

import br.eti.kge.OSApiApplication.domain.exception.DomainException;
import br.eti.kge.OSApiApplication.domain.model.Cliente;
import br.eti.kge.OSApiApplication.domain.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author devsys-a
 */
@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;
    public Cliente salvar(Cliente cliente){
        Cliente clienteExistente = clienteRepository.findByEmail(cliente.getEmail());
        
        //Lembre-se que o método SAVE pode ser usado para atualizar um cliente também!!
        // ID vazio --> Novo Registro
        // ID Preenchido --> Alterar existente
        
        //Verifica se o cliente existe
        if(clienteExistente != null && !clienteExistente.equals(cliente)){
            //Lançar exception
            
            throw new DomainException("Já existe um cliente cadastrado com esse email!");
        }
    
        return clienteRepository.save(cliente);
    
    }
    
    public void excluir(Long clienteId){
        clienteRepository.deleteById(clienteId);
    }
}
