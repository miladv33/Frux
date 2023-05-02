# Frux - Android Image Search App

This is an Android image search app that allows users to search for images by entering one or more words in a text field. The app requests the Pixabay API to show the images associated with the text provided by the user and displays a list of results.

## Requirements

### General
- The project must be able to compile and run on Android 7.0 and higher
- The app should follow the official Material Design guidelines
- The main language is Kotlin
- The code is:
  - Organized
  - Efficient
  - Readable
  - Decoupled
  - Follow best practices (e.g. DRY, SOLID)
  - With the ability to handle configuration changes
  
### Tech Stack
- MVVM Architecture
- CLEAN Architecture
- Jetpack Compose
- Coroutines
- Hilt
- Coil
- Retrofit
- Room


### Prerequisites
- An API key for the Pixabay public web services. It can be retrieved from this page (you must be logged in to see it):
  https://pixabay.com/api/docs/#api_search_images

## Features
- User can search for images by entering one or more words in a text field
- The app requests the Pixabay API to show the images associated with the text provided by the user and displays a list of results
- Each entry in the list displays:
  - A thumbnail of the image
  - The Pixabay username
  - A list of the image’s tags
- The app caches the results for offline handling
- A click on the list item opens a dialog asking the user if they want to see more details
- In case of a positive answer, a new detail screen is opened that displays:
  - A bigger version of the image
  - The name of the user
  - A list of the image’s tags
  - The number of likes
  - The number of downloads
  - The number of comments
- The app triggers a search for the string “fruits” when it starts

## Installation
1. Clone or download the repository
2. Open the project in Android Studio
3. Add your Pixabay API key to the `gradle.properties` file:
   ```
   api_key="PIXABAY_API_KEY"
   ```
4. Build and run the project

## License
This project is licensed under the MIT License - see the LICENSE file for details.
