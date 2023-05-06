package com.example.movies.di

import com.example.movies.data.remote.MovieService
import com.example.movies.data.remote.RemoteDataSource
import com.example.movies.data.repository.MovieRepositoryImpl
import com.example.movies.domain.repository.MovieRepository
import com.example.movies.domain.usecase.GetMovieUsesCase
import com.example.movies.provideDispatcher
import org.koin.dsl.module

private val dataModule = module {
    factory {
        RemoteDataSource(get(),get())
    }
    factory {
        MovieService()
    }
}
private val utilityModule = module {
    factory { provideDispatcher() }
}
private val domainModule = module {
    single<MovieRepository> {
        MovieRepositoryImpl(get())
    }
    factory { GetMovieUsesCase() }
    factory { GetMovieUsesCase() }
}
private val sharedModules = listOf(domainModule, dataModule, utilityModule)
fun getSharedModules() = sharedModules