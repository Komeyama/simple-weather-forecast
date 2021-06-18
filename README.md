# Simple Weather app
This is a simple Japanese weather forecast app for Android. 
Weather forecast data is gathered from [OpenWeatherMap](https://openweathermap.org/).
The purpose of this repository is to learn about the modern architecture and libraries of Android app development. 
Referring to [conference-app-2020](https://github.com/DroidKaigi/conference-app-2020).

## Screenshot
|<img src="https://github.com/Komeyama/simple-weather-forecast/blob/master/art/screenshot/top.png" width="180">|<img src="https://github.com/Komeyama/simple-weather-forecast/blob/master/art/screenshot/cities.png" width="180">|<img src="https://github.com/Komeyama/simple-weather-forecast/blob/master/art/screenshot/search.png" width="180">|<img src="https://github.com/Komeyama/simple-weather-forecast/blob/master/art/screenshot/detail.png" width="180">|<img src="https://github.com/Komeyama/simple-weather-forecast/blob/master/art/screenshot/favorite.png" width="180">|
|:---:|:---:|:---:|:---:|:---:|
|Top Screen|City List Screen|Search Screen|Detail Screen|Favorite Screen|

## Usage
1. Pleage create an API key on [OpenWeatherMap](https://openweathermap.org/api).
1. Open the local.properties in your project level directory, and then add the following code. (Here we are using [Secrets Gradle Plugin for Android](https://github.com/google/secrets-gradle-plugin).)

    ```local.properties
    apiKey=YOUR_API_KEY
    ```
    
1. Save the file and sync your project with Gradle.

## Libraries
Here is [dependencies file](https://github.com/Komeyama/simple-weather-forecast/blob/master/buildSrc/src/main/java/dependencies/Dep.kt).

## License
[MIT License](https://github.com/Komeyama/simple-weather-forecast/edit/master/LICENSE.txt)
<pre><code>Copyright 2020 Komeyama

Permission is hereby granted, free of charge, 
to any person obtaining a copy of this software and associated documentation files (the "Software"), 
to deal in the Software without restriction, 
including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, 
and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, 
subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies 
or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, 
EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, 
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. 
IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, 
DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, 
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
</code></pre>
