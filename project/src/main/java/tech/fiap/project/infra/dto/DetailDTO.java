package tech.fiap.project.infra.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DetailDTO {

    private int cap;
    private String type;
    private int value;
}
