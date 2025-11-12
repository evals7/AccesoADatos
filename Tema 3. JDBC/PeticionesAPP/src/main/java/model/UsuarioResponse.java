package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UsuarioResponse {                  //clase para crear una lista que contenga los usuarioJSON

    private List<UsuarioJSON> users;

}
