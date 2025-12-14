package database;

public interface SchemaDB {
    String CURSO_TAB_NAME = "cursos";
    String CURSO_COL_ID = "id";
    String CURSO_COL_NAME = "nombre";
    String CURSO_COL_IDPROF = "id_profesor";

    String ESTUDIANTE_TAB_NAME = "estudiantes";
    String ESTUDIANTE_COL_ID = "id";
    String ESTUDIANTE_COL_NAME = "nombre";

    String PROF_TAB_NAME = "profesores";
    String PROF_COL_ID = "id";
    String PROF_COL_NAME = "nombre";

    String CURSOEST_TAB_NAME = "cursos_estudiantes";
    String CURSOEST_COL_ID_CURSO = "id_curso";
    String CURSOEST_COL_ID_ESTUDIANTE = "id_estudiantes";
}
