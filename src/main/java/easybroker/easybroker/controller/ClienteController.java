package easybroker.easybroker.controller;

import easybroker.easybroker.model.ClienteModel;
import easybroker.easybroker.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;


    @GetMapping(path = "/{codigo}")
    public ResponseEntity<?> buscarClientePorCodigo(@PathVariable  Long codigo){
        return new ResponseEntity<>(clienteService.buscarClientePorCodigo(codigo), HttpStatus.OK);
    }

    @GetMapping(path = "/clientesDeUmUsuario/{codigo}")
    public ResponseEntity<?> listarClientesDeUmUsuario(@PathVariable  Long codigo){
        return new ResponseEntity<>(clienteService.listarClientesDeUmUsuario(codigo), HttpStatus.OK);
    }

    @PostMapping(path = "/{codigo}")
    public ResponseEntity<?> adcionarCliente(@PathVariable  Long codigo,
                                             @RequestBody ClienteModel cliente){
        return new ResponseEntity<>(clienteService.adcionarCliente(codigo, cliente), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{codigo}")
    public ResponseEntity<?> excluirCliente(@PathVariable  Long codigo){
        return new ResponseEntity<>(clienteService.excluirCliente(codigo), HttpStatus.OK);
    }
}
