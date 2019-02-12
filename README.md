# WeatherApp :earth_americas:
Test de entrevista para aplicante para el puesto de Desarrollador

Desarrollo de una App Android que permita visualizar el pronóstico climático actual y de los
próximos 5 días en la ubicación actual y permita visualizar el pronóstico de otras 5 ciudades
seleccionables.

Lenguaje : Kotlin

## API's elegidas:

* [OpenWeatherMap](https://openweathermap.org/api) - Pronósticos meteorológicos
* [IP Geolocation API](https://ipwhois.io/) - Geolocalizacion por IP

## Librerías elegidas:

* [Retrofit](https://square.github.io/retrofit/) - Cliente REST HTTP
* [Picasso](http://square.github.io/picasso/) - Gestor de imagenes

## Desenlace de la aplicación
El desarrollo cumple con las funcionalidades principales, logre consumir los datos y 
parsearlos para presentarlos en la aplicación.
La aplicación esta armada con el patrón de arquitectura MVP(Model, View, Presenter).

Implemente la clase AppAplication que extiende de Aplication y es donde se configuran los 
servicios, donde también están disponibles para los activities que lo requieran. 

### Items a Mejorar
  - Concurrencia: En las múltiples llamas al servicio OpenWeatherMap para cargar el clima de 5 
    ciudades distintas los implemente con callbacks que hacen que para iniciar la búsqueda de 
    la siguiente ciudad tiene que terminar la primera. 
    Para solucionar esto se puede implementar una librería llamada 
    [ReactiveX](http://reactivex.io/) que proporciona un mejor manejo de las respuestas HTTP
  - Test: No hay implementado una batería de test para esta aplicación
