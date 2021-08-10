# The Brief:

The Pulselive Code Challenge App was built using MVVM approach and some concepts of Clean Architecture and Clean Coding.

The app is only modularized in directories inside the `app` module. Depending on the case, I prefer to using modularization with Android Modules and Libraries.

There are two main directories on the project: `common`, `features`.

`common`: is the directory that I have used to put some Interfaces, Abstract Classes, Classes, Extensions, etc, that should be shared with all features, e.g. `HttpClient.RetrofitClient`.

`features`: inside this directory you can find the main feature: `news`.

- `news`: this module has all resources, repositories, datasources, etc, related to the news/articles feature.

Inside each one I have followed this directory tree:

    features/news
    ├── data
        └── datasource
       		└── remote #NewsRemoteDatasource
        	└── local #Not available in this project
        └── repository #NewsRepositoryImpl
    ├── domain
        └── usecases (If needed, in this project I decided do not to use Use Cases)
        └── models
        └── repository #NewsRepository interface
    ├── presentation
        └── viewmodel #NewsViewModel
        └── fragment #NewsFragment and ArticleDetailsFragment
        └── activity #NewsActivity (application and navigation entrypoint)

You can find more directories than these listed above, but, usually, these are the base directories I have been using lately.

Between Screen and ViewModel I have used `States` to manage how the UI should handle data and states.

For example, when the view needs to switch view visibility from Visible to Invisible a `State` is emitted through `LiveData` to the Activity/Fragment changing the specific state that needs to be changed.

Example:
```
private val _newsUIStateFlow = MutableLiveData<NewsState>()  
val newsUIStateFlow: LiveData<NewsState> = _newsUIStateFlow

fun getNews() {  
    viewModelScope.launch {  
        _newsUIStateFlow.value = NewsState.Loading  
        runCatching {  
            newsRepository.getNews()  
        }.onSuccess {  
            _newsUIStateFlow.value = NewsState.Success(it.articles)  
        }.onFailure {  
            _newsUIStateFlow.value = NewsState.Failure(it.message)  
        }  
        _newsUIStateFlow.value = NewsState.Done  
    }  
}
```

And finally, inside the Activity/Fragment:

Example:
```
@AndroidEntryPoint
class MyUI : Fragment() {
	// view binding and viewmodel injection here....
	override fun onViewCreated(.....) {
	     setupObservers()
	}
	private fun setupObservers() {  
       viewModel.newsUIStateFlow.observe(viewLifecycleOwner) { state ->  
           when (state) {  
                is NewsState.Loading -> showLoading()  
                is NewsState.Success -> showDataOnUI(state.articles)  
                is NewsState.Failure -> showErrorOnUI(state.message ?: getDefaultErrorMessage())  
                is NewsState.Done -> hideLoading()  
           }  
       }  
    }
}
```
For dependency injection, I have used Dagger Hilt. In the past 2 years, all projects that I have been working on were built using Koin, which is basically a Service Locator, or pure Dagger. I like both, but I am really enjoying how Google has improved Dagger's usability.

For network request I am using Retrofit + OkHttpClient + Gson.

For unit tests: JUnit4 + MockK. I also have experience using jUnit 5. I did not use jUnit 5 on this project for convenience only.
For Automated Tests: Espresso + MockWebServer + a little robot pattern to help me testing UI.
