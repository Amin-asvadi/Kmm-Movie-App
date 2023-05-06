package com.example.movies

import com.example.movies.util.Dispatcher
import com.example.movies.util.IosDispatcher

internal actual fun provideDispatcher(): Dispatcher = IosDispatcher()