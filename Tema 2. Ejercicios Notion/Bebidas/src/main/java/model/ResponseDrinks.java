package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor

                                            //1. una clase ResponseDrinks, que nos devolverá una lista de bebidas, no un objeto. Esa clase tiene un atributo List privado del que no sabemos el tipo, por tanto lo tiparemos como Object
public class ResponseDrinks {
    private List<Drink> drinks;             //1.1 El atributo drinks es, según observamos en el JSON, una lista de objetos. Como no podemos tiparla porque no sabemos de qué tipo es la lista, pondremos temporalmente Object hasta que creemos el objeto que lo reemplazará (Drink)
}
