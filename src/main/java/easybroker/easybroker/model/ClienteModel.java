package easybroker.easybroker.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "Cliente")
@Getter
@Setter
@Table(name = "clientes")
public class ClienteModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    private String dataCadastro;

    private String nome;

    private String cpf;

    private String dataNascimento;

    private String descricao;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "arquivo_id")
    private List<ArquivoModel> arquivos = new ArrayList<>();
}
