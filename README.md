# Simple Weather app
A simple weather app for Android. Weather forecast data is gathered from [OpenWeatherMap](https://openweathermap.org/).


|<img src="https://github.com/Komeyama/simple-weather-forecast/blob/master/art/screenshot/top.png" width="180">|<img src="https://github.com/Komeyama/simple-weather-forecast/blob/master/art/screenshot/cities.png" width="180">|<img src="https://github.com/Komeyama/simple-weather-forecast/blob/master/art/screenshot/search.png" width="180">|<img src="https://github.com/Komeyama/simple-weather-forecast/blob/master/art/screenshot/detail.png" width="180">|<img src="https://github.com/Komeyama/simple-weather-forecast/blob/master/art/screenshot/favorite.png" width="180">|
|---|---|---|---|---|
|Top Screen|City List Screen|Search Screen|Detail Screen|Favorite Screen|

## Usage
1. Pleage create an API key on [OpenWeatherMap](https://openweathermap.org/api).
1. Open the local.properties in your project level directory, and then add the following code. (Here we are using [Secrets Gradle Plugin for Android](https://github.com/google/secrets-gradle-plugin).)

    ```local.properties
    apiKey=YOUR_API_KEY
    ```
    
1. Save the file and sync your project with Gradle.

## License

[MIT](https://github.com/Komeyama/simple-weather-forecast/edit/master/LICENSE.txt)
