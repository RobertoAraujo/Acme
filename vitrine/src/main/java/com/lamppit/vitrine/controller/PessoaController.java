package com.lamppit.vitrine.controller;

import com.lamppit.vitrine.model.entity.PessoaEntity;
import com.lamppit.vitrine.repository.PessoaRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class PessoaController {

    @Autowired
    private PessoaRepository pessoaRepository;

    @ApiOperation(value = "Retorna uma lista de pessoas")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna a lista de pessoa"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @RequestMapping(value = "/pessoa", method = RequestMethod.GET, produces = "application/json")
    public List<PessoaEntity> Get() {
        return pessoaRepository.findAll();
    }

    @RequestMapping(value = "/pessoa/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<PessoaEntity> GetById(@PathVariable(value = "id") long id) {
        Optional<PessoaEntity> pessoa = pessoaRepository.findById(id);
        return pessoa.map(pessoaEntity -> new ResponseEntity<>(pessoaEntity, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @RequestMapping(value = "/pessoa", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public PessoaEntity Post(@Valid @RequestBody PessoaEntity pessoa) {
        return pessoaRepository.save(pessoa);
    }

    @RequestMapping(value = "/pessoa/{id}", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
    public ResponseEntity<PessoaEntity> Put(@PathVariable(value = "id") long id, @Valid @RequestBody PessoaEntity newPessoa) {
        Optional<PessoaEntity> oldPessoa = pessoaRepository.findById(id);
        if (oldPessoa.isPresent()) {
            PessoaEntity pessoa = oldPessoa.get();
            pessoa.setNome(newPessoa.getNome());
            pessoaRepository.save(pessoa);
            return new ResponseEntity<PessoaEntity>(pessoa, HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/pessoa/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity<Object> Delete(@PathVariable(value = "id") long id) {
        Optional<PessoaEntity> pessoa = pessoaRepository.findById(id);
        if (pessoa.isPresent()) {
            pessoaRepository.delete(pessoa.get());
            return new ResponseEntity<>(HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
