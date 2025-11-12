package model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties (ignoreUnknown = true)   //ignorar las que no aparezcan
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioJSON {                       //CLASE PARA IMPORTAR EL JSON (USERS) (solo las propiedades declaradas https://dummyjson.com/users

    //IMPORTAR JSON

    private String firstName;
    private String email;
    private int age;  //inicialmente debería ser String phone, pero nos da error el formato, porque en la BBDD lo declaramos int...


}
