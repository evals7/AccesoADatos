package controller;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Drink;
import model.ResponseDrinks;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class DrinkController {                                  //3. Clase DrinkController donde recogeremos toda la lógica de la aplicación

    public void consultarNombre(String nombre){                  //3.1 Metodo para consultar por nombre, pasándole el nombre
        ObjectMapper mapper = new ObjectMapper();                                                               //3.4 Instanciamos el objeto de mapper
        try {
            URL url = new URL("https://www.thecocktaildb.com/api/json/v1/1/search.php?s=" + nombre);        //3.2 Instanciamos la URL donde vamos a buscar, y capturamos la excepción (por si la URL está mal)
                                                                                                                    //3.3 En caso de que esté bien, vamos a comenzar el mapeador, con ObjectMapper

            ResponseDrinks responseDrinks = mapper.readValue(url, ResponseDrinks.class);           //3.5 y leemos el valor con readValue(url, clase que usara como plantilla para mapear.class), se lo asignamos a un objeto de ResponseDrinks (responseDrinks) y capturamos la excepción

            if (responseDrinks.getDrinks() != null){                           //3.7 lo encajamos dentro de un ifelse por si el nombre aportado en la entrada (app) no existiera
                for (Drink item: responseDrinks.getDrinks()){                 //3.6 Recorremos todas las bebidas de responseDrinks.getDrinks con un for
                    System.out.println(item);
                }
            } else {
                System.out.println("No hay cócteles con ese nombre");
            }

        } catch (MalformedURLException e) {
            System.out.println("La URL no es válida");
        } catch (StreamReadException e) {
            System.out.println("Lectura incorrecta");
        } catch (DatabindException e) {
            System.out.println("Los tipos de datos no coinciden");
        } catch (IOException e) {
            System.out.println("Error de internet");
        }
    }


    public void consultarLetra(String letra){
        ObjectMapper mapper = new ObjectMapper();
        try {
            URL url = new URL("https://www.thecocktaildb.com/api/json/v1/1/search.php?f=" + letra);
            ResponseDrinks responseDrinks = mapper.readValue(url, ResponseDrinks.class);

            if (responseDrinks.getDrinks() != null) {
                for (Drink item : responseDrinks.getDrinks()) {
                    System.out.println(item);
                }
            }else{
                System.out.println("No hay cócteles con esa letra");
            }


        } catch (MalformedURLException e) {
            System.out.println("la URL no es válida");
        } catch (StreamReadException e) {
            System.out.println("Lectura incorrecta");
        } catch (DatabindException e) {
            System.out.println("Los tipos de datos no coinciden");
        } catch (IOException e) {
            System.out.println("Error de internet");
        }

    }

    public void consultarLetraNombre(String palabra, String busqueda){  //5. Como los dos metodos de arriba son casi iguales (solo cambia la URL y letra o nombre) podemos elaborar un metodo comun al que le podamos pasar la palabra (nombre o letra) y la búsqueda f u o, según la URL que queramos completar
        ObjectMapper mapper = new ObjectMapper();
        try {
            URL url = new URL("https://www.thecocktaildb.com/api/json/v1/1/search.php?"+busqueda+"="+palabra);      //5.1 modificamos la URL para sustituir las letras f o s por "búsqueda" + palabra (nombre o letra)
            ResponseDrinks responseDrinks = mapper.readValue(url, ResponseDrinks.class);
            if (responseDrinks.getDrinks() !=null){
                for (Drink item: responseDrinks.getDrinks()) {
                    System.out.println(item);
                }
            } else {
                System.out.println("No hay cocktails con ese nombre");
            }


        } catch (MalformedURLException e) {
            System.out.println("La URL es invalida");
        } catch (StreamReadException e) {
            System.out.println("Lectura incorrecta");
        } catch (DatabindException e) {
            System.out.println("Los tipos de datos no coinciden");
        } catch (IOException e) {
            System.out.println("Error de internet");
        }
    }

    public void obtenerAleatorio(){

        ObjectMapper mapper = new ObjectMapper();
        try {
            URL url = new URL("https://www.thecocktaildb.com/api/json/v1/1/random.php");
            ResponseDrinks responseDrinks = mapper.readValue(url, ResponseDrinks.class);
            System.out.println(responseDrinks.getDrinks().getFirst());

        } catch (MalformedURLException e) {
            System.out.println("Mal escrita");
        } catch (StreamReadException e) {
            System.out.println("Fallo en la lectura");
        } catch (DatabindException e) {
            System.out.println("Fallo en la asociación");
        } catch (IOException e) {
            System.out.println("Fallo en internet");
        }
    }

    public void guardarFavorito(){
        ObjectMapper mapper =  new ObjectMapper();
       //
    }
}