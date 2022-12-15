# MovieApp
Android App using [The Movie DB](https://www.themoviedb.org) API.

## Screenshots
<img src="https://user-images.githubusercontent.com/117205261/207936785-30e88dc6-ba38-4e81-9a3e-fed6c84cc3fa.jpeg" width="320" hspace="40"><img src="https://user-images.githubusercontent.com/117205261/207936961-c9a4e92d-05fa-4b7e-86c1-d910434c8855.jpeg" width="320" hspace="40">

## Tech stack & Open-source libraries
- [Kotlin](https://kotlinlang.org/) based, [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) + [Flow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/) for asynchronous.
- Jetpack
  - Lifecycle: Observe Android lifecycles and handle UI states upon the lifecycle changes.
  - ViewModel: Manages UI-related data holder and lifecycle aware. Allows data to survive configuration changes such as screen rotations.
  - DataBinding: Binds UI components in your layouts to data sources in your app using a declarative format rather than programmatically.
  - Room: Constructs Database by providing an abstraction layer over SQLite to allow fluent database access.
  - [Hilt](https://dagger.dev/hilt/): for dependency injection.
- Architecture
  - MVVM Architecture (View - DataBinding - ViewModel - Model)
  - Repository Pattern
- [Retrofit2 & OkHttp3](https://github.com/square/retrofit): Construct the REST APIs and paging network data.
- [Glide](https://github.com/bumptech/glide): Loading images from network.
- [Timber](https://github.com/JakeWharton/timber): A logger with a small, extensible API.
- [Firebase App Distribution](https://firebase.google.com/docs/app-distribution): Upload APKs to App Distribution and distribute builds to testers.

- TODO
  - [Paging](https://developer.android.com/topic/libraries/architecture/paging/)
  - [WorkManager](https://developer.android.com/topic/libraries/architecture/workmanager/)
  - [Compose](https://developer.android.com/jetpack/compose)

## Open API
App using the [The Movie DB](https://www.themoviedb.org) API for constructing RESTful API.
