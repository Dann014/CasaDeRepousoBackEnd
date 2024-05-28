package Trabalho3.semestre.Main.DTO;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CasaDTO {
    private Long id;
    private String nome;
    private Integer numero;
    private String tipo;
    private String descricao;
    private Double preco;
    private Boolean disponivel;


}
