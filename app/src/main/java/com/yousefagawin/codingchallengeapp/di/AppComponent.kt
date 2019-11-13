package com.yousefagawin.codingchallengeapp.di

import android.app.Application
import com.yousefagawin.codingchallengeapp.App
import com.yousefagawin.codingchallengeapp.di.modules.AppModule
import com.yousefagawin.codingchallengeapp.di.modules.main.MainActivityModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    //put here the AppModule and the fragments, services & activity modules at com.di.modules
    AndroidSupportInjectionModule::class,
    AppModule::class,
    MainActivityModule::class

])
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: App)
}