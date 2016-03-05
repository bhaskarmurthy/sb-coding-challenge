# Sensibill Coding Challenge

## Architecture
* Single-activity application, contains a `RecyclerView` fragment to display a list of receipts from API. The recycler view fragment shows list of items in a list or grid based on display size for responsive layout.
* **Model-View-Presenter** design pattern is used to separate data from presentation.
* `Dagger2` is used for dependency injection to provide instances of objects such as API, presenter, view etc.
* `RxAndroid` and `Retrofit` is used for asynchronous access to API using functional reactive extentions.
* Unit tests exercise the presenter's business logic with mock API, possible due to dependency injection.
