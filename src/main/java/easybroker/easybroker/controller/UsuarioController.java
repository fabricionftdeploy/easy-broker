package easybroker.easybroker.controller;

import easybroker.easybroker.dto.request.CadastroRequestDTO;
import easybroker.easybroker.dto.request.LoginRequestDTO;
import easybroker.easybroker.dto.response.UsuarioResponseDTO;
import easybroker.easybroker.model.UsuarioModel;
import easybroker.easybroker.service.UsuarioService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ModelMapper modelMapper;


    @GetMapping
    public ResponseEntity<?> listarUIsuarios(){
        return new ResponseEntity<>(converterEmListaDeResponseDTO(usuarioService.listarUsuarios()), HttpStatus.OK);
    }

    @GetMapping(path = "/{codigo}")
    public ResponseEntity<?> buscarUsuarioPorCodigo(@PathVariable Long codigo){
        return new ResponseEntity<>(converterEmResponseDTO(usuarioService.buscarUsuarioPorCodigo(codigo)), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> salvarUsuario(@RequestBody CadastroRequestDTO cadastroRequest){
        return new ResponseEntity<>(converterEmResponseDTO(usuarioService.salvarUsuario(cadastroRequest)), HttpStatus.CREATED);
    }

    @PostMapping(path = "/login")
    public ResponseEntity<?> salvarUsuario(@RequestBody LoginRequestDTO loginRequest){
        return new ResponseEntity<>(usuarioService.fazerLogin(loginRequest), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> excluirUsuarios(){
        return new ResponseEntity<>(usuarioService.excluirUsuarios(), HttpStatus.OK);
    }


    //privados
    public List<UsuarioResponseDTO> converterEmListaDeResponseDTO(List<UsuarioModel> usuarios){
        List<UsuarioResponseDTO> usuariosDTO = new ArrayList<>();

        for (UsuarioModel usuario: usuarios)
            usuariosDTO.add(converterEmResponseDTO(usuario));

        return usuariosDTO;
    }

    public UsuarioModel converterEmModel(CadastroRequestDTO cadastroRequest){
        return modelMapper.map(cadastroRequest, UsuarioModel.class);
    }

    public UsuarioResponseDTO converterEmResponseDTO(UsuarioModel usuario){
        return modelMapper.map(usuario, UsuarioResponseDTO.class);
    }
}
