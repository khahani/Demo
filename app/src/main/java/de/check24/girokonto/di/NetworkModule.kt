package de.check24.girokonto.di

import dagger.Module
import dagger.Provides
import de.check24.girokonto.data.source.api.Api

@Module
class NetworkModule {

    @Provides
    fun provideApi(): Api = Api.create()


}