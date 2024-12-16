

**# Trabajo Práctico 2 - Manipulación de JSON**

Este programa permite realizar distintas operaciones sobre un JSON ingresado por entrada estándar, tales como agregar claves, obtener valores específicos y verificar la existencia de claves.

**## Requisitos**

- **Scala**: El programa requiere Scala 3.3.4 instalado para compilar y ejecutarse.
- **Entorno de Línea de Comandos**: Debe ejecutarse en un entorno de línea de comandos, como una terminal Unix o el CMD de Windows.

**## Compilación**

Para generar el executable JAR, asegúrate de estar en el directorio raíz del proyecto y ejecuta:

```bash
sbt assembly
```

**## Ejecución **

Nota: En casos generales, el formato esperado para los objetos pasados como parametro por consola es el siguiente.
```
 {\"Objeto\":{\"ClaveSubobjeto\":\"ValorSubobjeto\"}}
```

Nota: El programa espara paths en la notacion que no contienen "|".

Para ejecutar el programa a traves de uso de redireccionamiento de un archivo como entrada estandar, ejecuta:

```
java -jar target/scala-3.3.4/tp2-assembly-0.1.0-SNAPSHOT.jar <operacion> <parametros> < <archivo.json>
```

Para ejecutar el programa dos veces, generando una operacion compuesta, encadenado los resultados unsando pipes :

```
java -jar target/scala-3.3.4/tp2-assembly-0.1.0-SNAPSHOT.jar <operacion1> <parametros1> < <archivo.json> | java -jar target/scala-3.3.4/tp2-assembly-0.1.0-SNAPSHOT.jar <operacion2> <parametros2>
```

