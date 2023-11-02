package easybroker.easybroker.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CadastroRequestDTO {

    private String nomeCompleto;
    private String autonomo;
    private String nomeImobiliaria;
    private String creci;
    private String email;
    private String senha;
}
