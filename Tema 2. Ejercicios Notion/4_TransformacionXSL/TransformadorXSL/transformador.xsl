<?xml version="1.0" encoding="UTF-8" ?>
<stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:template match="/">
        <html>
            <head>
                <meta charset="utf-8"/>
                <meta name="viewport" content="width=device-width, initial-scale=1"/>
                <title>Tienda de productos</title>
                <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet"
                      integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB"
                      crossorigin="anonymous"/>
            </head>
            <body>
                <div class="container">
                    <h1>Listado de productos</h1>
                    <div class="row">
                        <xsl:for-each select="productos/producto">
                            <div class="col">
                                <div class="card" style="width: 18rem;">
                                    <div class="card-body">
                                        <h5 class="card-title">
                                            <xsl:value-of select="nombre"/>
                                        </h5>
                                        <p class="card-text text-success fw-bold">
                                            <xsl:value-of select="precio"/>
                                        </p>
                                        <p class="card-text">
                                            <xsl:choose>
                                                <xsl:when test="disponible='true'">
                                                    <span class="text-success">Disponible</span>
                                                </xsl:when>
                                                <xsl:otherwise>
                                                    <span class="text-danger">No disponible</span>
                                                </xsl:otherwise>
                                            </xsl:choose>
                                        </p>
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