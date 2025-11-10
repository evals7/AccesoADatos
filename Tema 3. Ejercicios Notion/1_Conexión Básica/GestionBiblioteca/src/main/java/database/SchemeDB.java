package database;

public interface SchemeDB {
    String DB_NAME = "biblioteca";

    String TABLIBROS_NAME = "libros";
    String COLLI_NAME = "titulo";
    String COLLI_ISBN ="isbn";
    String COLLI_AUTOR ="autor";
    String COLLI_EDIT ="editorial";
    String COLLI_YEAR = "ano_publicacion";
    String COLLI_CATH = "categoria";
    String COLLI_PRICE = "precio";
    String COLLI_STOCK = "stock";
    String COLLI_ID = "id";

    String TABPREST_NAME = "prestamos";
    String COLPR_NAME = "nombre_usuario";
    String COLPR_FECHA = "fecha_prestamo";
    String COLPR_STATUS = "estado";
    String COLPR_ID = "id";
    String COLPR_IDLIBRO= "libro_id";

}
