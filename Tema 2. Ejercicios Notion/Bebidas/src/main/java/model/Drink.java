package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)                                 //2.2 COmo no hemos cogido todos los atributos, necesitamos que esta anotación avise para ignorar las que no estén declaradas

public class Drink {                                                        //2. Cuando vemos el atributo y la lista de elementos que tenemos, podemos convertir ese atributo en una clase objeto Drink, y sus atributos serán los elementos de la lista. Este nuevo objeto Drink, es el que usaremos para reemplazar el tipo del atributo de ResponseDrinks

    private String idDrink,strDrink,strTags,strCategory,strInstructionsES,
            strDrinkThumb,strIngredient1,strIngredient2;                    //2.1 Tenemos que añadir todos los atributos de una bebida que contiene la API. Como son muchos y no queremos escribirlos a mano, vamos a pasarlos por el quciktype (quicktype no funciona así, que cogemos un par de atributos
}
