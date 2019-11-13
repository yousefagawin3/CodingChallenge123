package com.yousefagawin.codingchallengeapp.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.yousefagawin.codingchallengeapp.di.FactoryViewModel
import com.yousefagawin.codingchallengeapp.di.ViewModelKey
import com.yousefagawin.codingchallengeapp.ui.main.MainActivityViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    //put here all the ViewModels of fragments & activities
    @Binds
    @IntoMap
    @ViewModelKey(MainActivityViewModel::class)
    abstract fun bindMainActivityViewModel(viewModel: MainActivityViewModel): ViewModel


    @Binds
    abstract fun bindViewModelFactory(factory: FactoryViewModel): ViewModelProvider.Factory
}