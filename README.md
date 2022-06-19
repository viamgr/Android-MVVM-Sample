# Near By Example using TomTom Api. 
In this project we want to have an application that displays the locations around the user's current location using the com.tomtom.developer APIs.

## A project with Mvvm architecture And Compose that loads the nearby locations

Technologies: 
* Clean Architecture
* MVVM
* Compose
* Coroutine
* Flow
* Paging 3
* Modular Design
* Retrofit
* Room
* Testable Design
* Accompanist

# Challenge


The requirements are as follows:
* The program has 2 pages, a page to display a list of places around the user's current geographical location and a page to display details of each location.
* Ability to cache information in such a way that if the user's position did not change much on subsequent visits to the program (for example, the user's location was only moved 100 meters), the data would not need to be retrieved from the Internet. This means that even when the user does not have access to the Internet, the latest data received is displayed to the user and a new list is received only if the information is accessible to the Internet or the user is old and moved.
* When the user's geographical location changes, Surrounding locations are also changed.
* The list on the page is Endless. You do not have to have all the location information received from TomTom on this page.