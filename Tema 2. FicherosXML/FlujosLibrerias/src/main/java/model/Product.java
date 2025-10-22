package model;
//CLASE INSTANCIAR LOS PRODUCTOS QUE QUEREMOS LEER DEL JSON

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.time.OffsetDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)     //2. Anotacion Jackson para indicar que ignore las propiedades que no se declaran abajo
@Data                                           //3. Getter y setter
public class Product {                          //1. creamos la clase con los 4 atributos que queremos del json


    public class Welcome {                      //hemos sustituido los atributos creados manualmente por la clase sacada del json con quickType, para tener todos los atributos del json escritos de forma automática
        private long id;
        private String title;
        private String description;
        private String category;
        private double price;
        private double discountPercentage;
        private double rating;
        private long stock;
        private String[] tags;
        private String brand;
        private String sku;
        private long weight;
        private Dimensions dimensions;
        private String warrantyInformation;
        private String shippingInformation;
        private String availabilityStatus;
        private Review[] reviews;
        private String returnPolicy;
        private long minimumOrderQuantity;
        private Meta meta;
        private String[] images;
        private String thumbnail;
    }

    class Dimensions {
        private double width;
        private double height;
        private double depth;
    }

    class Meta {
        private OffsetDateTime createdAt;
        private OffsetDateTime updatedAt;
        private String barcode;
        private String qrCode;
    }

    class Review {
        private long rating;
        private String comment;
        private OffsetDateTime date;
        private String reviewerName;
        private String reviewerEmail;
    }

}
