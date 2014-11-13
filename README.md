Android Dictionary under MVP architecture
=========================================

This project is build under the [MVP architecture pattern](http://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93presenter).
You are free to use the IDE you want, but I recommend AndroidStudio because is Gradle based and will be soon the official IDE supported by Google.


Data Layer
----------
Android & Java based, the data layer is the bottom this of our project.
In order to test the connections the data layer unit tests use [MockWebServer](https://github.com/square/okhttp/tree/master/mockwebserver) a fake web server to avoid hitting the real server.
I like this framework because decouples the API component from others, so we have an scalable and more testeable code.
In this layer the project uses Mockito and JUnit.
To avoid build a backend API faster we have a lot of options, like Amazon EC2, Google Compute Engine, or Microsoft Windows Azure. But this is the fastest project ever build ;) so it uses the platform [Wordnik](http://developer.wordnik.com/) that provides a simple dictionary API.


References
----------
Thanks to my friend Fran!

Inspired by:
* http://fernandocejas.com/2014/09/03/architecting-android-the-clean-way/