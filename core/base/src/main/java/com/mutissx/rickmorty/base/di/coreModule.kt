package com.mutissx.rickmorty.base.di

import com.google.gson.Gson
import com.mutissx.rickmorty.base.api.ErrorParser
import com.mutissx.rickmorty.base.testing.DefaultDispatcherProvider
import com.mutissx.rickmorty.base.testing.DispatcherProvider
import org.koin.dsl.module


val coreModule = module {
  single { ErrorParser(get()) }
  single { Gson() }
  single<DispatcherProvider> { DefaultDispatcherProvider() }
}
