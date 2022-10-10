# Movies-Compose-Architecure [Work in progress 🚧]
App built using jetpack compose with Android recommended Architecture and latest Android Development libraries such as: Dagger (Dependency Injection)
Room (SQL Database)

### API keys
You need to supply API / client keys for the various services the app uses:

[TMDB](https://www.themoviedb.org/documentation/api?language=es-ES)

Once you obtain the keys, you can set them in your ~/.gradle/gradle.properties:

## Architecture
The App follows the official architecture guided by [Google](https://developer.android.com/topic/architecture)

<img src="https://user-images.githubusercontent.com/10743855/194925920-a3da4328-9f22-4c6f-88dd-68a970430f9a.png" width="250" />

The architecture follows a reactive programming model with unidirectional data flow. With the data layer at the bottom, the key concepts are:

- Higher layers react to changes in lower layers.

- Events flow down.

- Data flows up.

- The data flow is achieved using streams, implemented using Kotlin Flows.

The data layer is implemented as an offline-first source of app data and business logic. It is the source of truth for all data in the app.

## Testing

To facilitate testing of components, the app uses dependency injection with Hilt.

Most data layer components are defined as interfaces. 
Then, concrete implementations (with various dependencies) are bound to provide those interfaces to other components in the app. 
In tests, the app does not use any mocking libraries. 
Instead, the production implementations can be replaced with test doubles using Hilt's testing APIs (or via manual constructor injection for ViewModel tests).

These test doubles implement the same interface as the production implementations and generally provide a simplified (but still realistic) implementation with additional testing hooks. This results in less brittle tests that may exercise more production code, instead of just verifying specific calls against mocks.
