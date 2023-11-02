package easybroker.easybroker.service;

import easybroker.easybroker.exception.RequestException;
import easybroker.easybroker.model.ClienteModel;
import easybroker.easybroker.model.UsuarioModel;
import easybroker.easybroker.repository.ClienteRepository;
import easybroker.easybroker.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    public ClienteModel buscarClientePorCodigo(Long codigo){
        return clienteRepository.findByCodigo(codigo)
                .orElseThrow(() -> new RequestException("Cliente inexistente!"));
    }

    public List<ClienteModel> listarClientesDeUmUsuario(Long codigo){
        return buscarUsuarioPorCodigo(codigo).getClientes();
    }

    public ClienteModel adcionarCliente(Long codigo, ClienteModel cliente){
        SimpleDateFormat formatar = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        cliente.setDataCadastro(formatar.format(Calendar.getInstance().getTime()));

        UsuarioModel usuario = buscarUsuarioPorCodigo(codigo);
        usuario.getClientes().add(cliente);
        usuarioRepository.save(usuario);
        return cliente;
    }

    public ClienteModel atualizarCliente(Long codigo, ClienteModel cliente){
        ClienteModel clienteAtualizado = buscarClientePorCodigo(cliente.getCodigo());

        UsuarioModel usuario = buscarUsuarioPorCodigo(codigo);
        usuario.getClientes().add(cliente);
        usuarioRepository.save(usuario);
        return cliente;
    }

    public String excluirCliente(Long codigo){
        clienteRepository.delete(buscarClientePorCodigo(codigo));
        return "Cliente excluído com sucesso";
    }


    //Métodos privados
    public UsuarioModel buscarUsuarioPorCodigo(Long codigo){
        return usuarioRepository.findByCodigo(codigo)
               .orElseThrow(() -> new RequestException("Usuário inexistente"));
    }
}
