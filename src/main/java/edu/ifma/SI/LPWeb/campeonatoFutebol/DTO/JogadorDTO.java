package edu.ifma.SI.LPWeb.campeonatoFutebol.DTO;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JogadorDTO {
    private Integer id;

    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @NotNull(message = "Data de nascimento é obrigatória")
    @Past(message = "Data de nascimento deve ser no passado")
    private LocalDate nascimento;

    @NotNull(message = "Altura é obrigatória")
    @DecimalMin(value = "1.0", message = "Altura mínima é 1.0m")
    @DecimalMax(value = "2.5", message = "Altura máxima é 2.5m")
    private Float altura;

    private Integer timeId;
}