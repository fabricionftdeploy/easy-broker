package easybroker.easybroker.controller;

import easybroker.easybroker.service.ArquivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/arquivo")
public class ArquivoController {

    @Autowired
    private ArquivoService arquivoService;
    

    @PostMapping(path = "/{codigoCliente}")
    public ResponseEntity<?> salvarArquivo(@PathVariable Long codigoCliente,
                                           @RequestParam MultipartFile arquivoBruto){
        return new ResponseEntity<>(arquivoService.salvarArquivo(codigoCliente, arquivoBruto), HttpStatus.OK);
    }

}
