package com.mutissx.rickmorty.di

import com.mutissx.rickmorty.base.di.coreModule
import com.mutissx.rickmorty.data.di.dataModule
import com.mutissx.rickmorty.domain.di.domainModule
import com.mutissx.rickmorty.persistence.di.persistenceModule
import org.koin.core.module.Module

val koinModules: List<Module> = listOf(
    coreModule,
    dataModule,
    domainModule,
    mainModule,
    persistenceModule
)