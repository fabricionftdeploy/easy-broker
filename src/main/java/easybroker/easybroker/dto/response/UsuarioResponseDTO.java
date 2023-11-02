package easybroker.easybroker.dto.response;

import easybroker.easybroker.model.ClienteModel;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UsuarioResponseDTO {

    private Long codigo;
    private String dataCadastro;
    private String nomeCompleto;
    private String autonomo;
    private String nomeImobiliaria = null;
    private String creci;
    private String email;
    private String role;
    private List<ClienteModel> clientes;
}
