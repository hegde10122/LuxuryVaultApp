package com.luxuryvault.android.core.util

sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)
    class Loading<T>(data: T? = null) : Resource<T>(data)

    companion object {
        fun <T> success(data: T): Resource<T> = Success(data)
        fun <T> error(msg: String, data: T? = null): Resource<T> = Error(msg, data)
        fun <T> loading(data: T? = null): Resource<T> = Loading(data)
    }
}

/**
Resource is not about data, it’s about data state.

It helps ViewModels and Composables talk in the same structured language:

when (val state = uiState.imageSearchResult) {
is Resource.Loading -> LoadingSpinner()
is Resource.Success -> ImageGrid(state.data)
is Resource.Error -> ErrorMessage(state.message)
}

Resource<T> - A wrapper class that represents Loading, Success, or Error for any data type.
To make UI and ViewModel communication cleaner and handle async states elegantly.

Benefits - Predictable state management, no null-check hell, smoother Compose UI updates.


A sealed class in Kotlin is a restricted class hierarchy —
it allows you to define a fixed set of subclasses that represent different variants of a concept.

You can think of it like a closed family of related classes.
Only the types you declare inside it are allowed to exist — nothing else can extend it.

Why Use Sealed Classes?

- Type-safe state management
You can handle different outcomes (like Success, Error, Loading) clearly.

- No magic numbers or enums
Instead of using strings or codes (“STATUS=200”), each state is an actual class.

- Smart compiler help
If you forget to handle a state in a when block, the compiler warns you
 */