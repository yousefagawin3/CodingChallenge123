package com.yousefagawin.codingchallengeapp.di.modules

import android.app.Application
import com.yousefagawin.codingchallengeapp.data.db.AppDatabase
import com.yousefagawin.codingchallengeapp.data.network.MyApiInterface
import com.yousefagawin.codingchallengeapp.data.network.NetworkConnectionInterceptor
import com.yousefagawin.codingchallengeapp.data.repository.MainRepository
import dagger.Module
import dagger.Provides

@Module(includes = [
    ViewModelModule::class
])
class AppModule {

    @Provides
    internal fun provideNetworkConnectionInterceptor(application: Application): NetworkConnectionInterceptor{
        return NetworkConnectionInterceptor(application)
    }

    @Provides
    internal fun provideMyApiInterface(networkConnectionInterceptor: NetworkConnectionInterceptor): MyApiInterface{
        return MyApiInterface(networkConnectionInterceptor)
    }


    @Provides
    internal fun provideAppDatabase(application: Application): AppDatabase{
        return AppDatabase(application)
    }

    @Provides
    internal fun provideMainRepository(myApiInterface: MyApiInterface, appDatabase: AppDatabase) : MainRepository {
        return MainRepository(myApiInterface, appDatabase)
    }

}