package com.yousefagawin.codingchallengeapp.di.modules.main

import com.yousefagawin.codingchallengeapp.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

//declare fragments, service, & activities like this
@Module
abstract class MainActivityModule {
    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity
}