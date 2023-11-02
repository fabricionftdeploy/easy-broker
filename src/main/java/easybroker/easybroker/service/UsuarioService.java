package easybroker.easybroker.service;

import easybroker.easybroker.dto.request.CadastroRequestDTO;
import easybroker.easybroker.dto.request.LoginRequestDTO;
import easybroker.easybroker.dto.response.LoginResponseDTO;
import easybroker.easybroker.exception.RequestException;
import easybroker.easybroker.model.UsuarioModel;
import easybroker.easybroker.repository.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public List<UsuarioModel> listarUsuarios(){
        return usuarioRepository.findAll();
    }

    public UsuarioModel buscarUsuarioPorCodigo(Long codigo){
        return usuarioRepository.findByCodigo(codigo)
                .orElseThrow(() -> new RequestException("Usuário inexistente"));
    }

    public UsuarioModel salvarUsuario(CadastroRequestDTO cadastroRequest){
        if(usuarioRepository.findByEmail(cadastroRequest.getEmail()).isPresent())
            throw new RequestException("Desculpe, este usuário já esta sendo utilizado, por favor defina outro!");

        SimpleDateFormat formatar = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        UsuarioModel usuario = modelMapper.map(cadastroRequest, UsuarioModel.class);

        usuario.setDataCadastro(formatar.format(Calendar.getInstance().getTime()));
        usuario.setRole("USER");
        usuario.setSenha(passwordEncoder.encode(cadastroRequest.getSenha()));

        return usuarioRepository.save(usuario);
    }

    public LoginResponseDTO fazerLogin(LoginRequestDTO loginRequest){
        UsuarioModel usuario = buscarUsuarioPorEmail(loginRequest.getEmail());

        if(passwordEncoder.matches(loginRequest.getSenha(), usuario.getSenha())){
            return new LoginResponseDTO(
                usuario.getCodigo(),
                usuario.getRole()
            );
        }else throw new RequestException("Senha incorreta!");
    }

    public String excluirUsuarios(){
        usuarioRepository.deleteAll();
        return "Usuários excluidos com sucesso!";
    }


    //Privados
    private UsuarioModel buscarUsuarioPorEmail(String email){
        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RequestException("Usuário inexistente"));
    }
}
