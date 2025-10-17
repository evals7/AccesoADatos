<?xml version="1.0" encoding="UTF-8" ?>                                     <!--1. Versión y codificación-->
<stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0"
            xmlns:https="http://www.w3.org/1999/xhtml">                     <!--2. Protocolo de normas. Namespace con todas las etiquetas de XSL -->
    <xsl:template
            match="/">                                                      <!--3. Aqui metemos el contenido que vamos a transformar. El atributo match nos indica a partir de qué nodo queremos transformar el xml -->
        <html>                                                              <!--4. La estructura del HTML que conocemos con head, body y título (opcional)-->
            <head>
                <meta charset="utf-8"/>
                <meta name="viewport" content="width=device-width, initial-scale=1"/>
                <title>Películas transformadas</title>
                <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet"
                      integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB"
                      crossorigin="anonymous"/>
            </head>
            <body>
                <div class="container mt-4">
                    <h1>Listado películas</h1>
                    <div class="row">                                           <!--5. Creamos un div para poner la clase row-->
                        <xsl:for-each select="peliculas/pelicula">              <!--6. para incluir todas las películas, necesitamos un for que recorra la lista y de cada película, sacar el atributo que queramos-->
                        <div class="col">                                       <!--5.1. Otro div con la clase col-->
                            <div class="card" style="width: 18rem;">            <!--6.1 Esta clase la hemos copiado de bootstrap buscando por "Card" y la vamos complementando con los value-of de cada nodo o atributo que queremos coger del xml-->
                                <div class="card-body">
                                    <xsl:variable name="imagen" select="@poster"/>     <!--7. Podemos crear una variable para atributos que no pueden salir a otra etiqueta, como en este caso @poster, que no puede salir a img src -->
                                    <img src="{$imagen}" class="card-img-top" alt="foto"/>
                                    <h5 class="card-title">
                                        <xsl:value-of select="@titulo"/>                <!--6.2 se irá a buscar @titulo y sacará el contenido de este (la @ para atributos)-->
                                    </h5>
                                    <p class="card-text">
                                        <xsl:value-of select="sinopsis"/></p>           <!--6.3 se irá a buscar sinopsis y sacará el contenido de este nodo-->
                                    <h5>Actores</h5>
                                    <ul class="list-group">                             <!--6.4 list-group es una clase de bootstrap-->
                                        <xsl:for-each select="personajes/personaje">    <!--6.5 otro foreach para recorrer los personajes, dentro meteremos un <li> para que se listen-->
                                            <li class="list-group-item">
                                                <xsl:value-of select="@actor"/>
                                            </li>
                                        </xsl:for-each>
                                    </ul>
                                </div>
                            </div>
                        </div>
                        </xsl:for-each>
                    </div>
                </div>
            </body>
        </html>
    </xsl:template>
</stylesheet>