package com.yousefagawin.codingchallengeapp

import android.app.Activity
import android.app.Application
import androidx.fragment.app.Fragment
import com.yousefagawin.codingchallengeapp.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class App: Application(), HasActivityInjector{


    //for activities
    @Inject
    lateinit var dispatchingActivityInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()

        initDagger()
    }


    //for activies in the app
    override fun activityInjector(): DispatchingAndroidInjector<Activity>? {
        return dispatchingActivityInjector
    }

    private fun initDagger() {
        //DaggerAppComponent is okay to be red at first
        DaggerAppComponent.builder().application(this).build().inject(this)
    }
}