# -- EXPLICACIÓN --

Usando Spring, consumir una API externa y almacenar los resultados en una base de datos MongoDB.

He aplicado una configuracion WebClient reactiva propia de Spring para la consumición de la API externa
[SWAPI](https://swapi.dev/); trate de usar *model mapper* para el paso de dto -> model, pero debido a un
ligero problema acabe haciéndolo a mano. Se aplicó la cache propia de Spring en el servicio y en el controlador.