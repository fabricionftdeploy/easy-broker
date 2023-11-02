package easybroker.easybroker.service;

import easybroker.easybroker.exception.RequestException;
import easybroker.easybroker.model.ArquivoModel;
import easybroker.easybroker.model.ClienteModel;
import easybroker.easybroker.repository.ArquivoRepository;
import easybroker.easybroker.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ArquivoService {

    @Autowired
    private ArquivoRepository arquivoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    public ArquivoModel salvarArquivo(Long codigoCliente, MultipartFile arquivoBruto){

        ArquivoModel arquivo = null;
        try {
            arquivo = new ArquivoModel(
              null,
              arquivoBruto.getOriginalFilename(),
              arquivoBruto.getOriginalFilename().substring(arquivoBruto.getOriginalFilename().lastIndexOf(".") + 1),
              arquivoBruto.getBytes()
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ClienteModel cliente = buscarClientePorCodigo(codigoCliente);

        cliente.getArquivos().add(arquivo);
        clienteRepository.save(cliente);

        return arquivo;
    }

    //Metódos privados
    public ClienteModel buscarClientePorCodigo(Long codigo){
        return clienteRepository.findByCodigo(codigo)
                .orElseThrow(() -> new RequestException("Cliente inexistente!"));
    }
}
