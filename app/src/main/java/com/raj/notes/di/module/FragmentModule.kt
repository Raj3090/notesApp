package com.raj.notes.di.module

import androidx.fragment.app.Fragment
import dagger.Module

@Module
class FragmentModule(private val fragment: Fragment) {
//
//    @Provides
//    fun providePhotoViewModel():PhotoViewModel{
//
//        return PhotoViewModel()
//
//    }
//
//    @Provides
//    fun provideProfileViewModel():ProfileViewModel{
//
//        return ProfileViewModel()
//
//    }
//
//
//    @Provides
//    fun provideSignUpViewModelViewModel(schedulerProvider: SchedulerProvider, networkHelper: NetworkHelper,
//                                        compositeDisposable: CompositeDisposable, userRepository: UserRepository, postRepository: PostRepository
//    ): HomeViewModel =
//
//        ViewModelProviders.of(fragment, ViewModelProviderFactory(HomeViewModel::class){
//
//            HomeViewModel(schedulerProvider,compositeDisposable,networkHelper,userRepository,postRepository)
//
//        }).get(HomeViewModel::class.java)
//
//    @Provides
//    fun provideLinearLayoutManager(): LinearLayoutManager = LinearLayoutManager(fragment.context)
//
//    @Provides
//    fun providePostsAdapter(): PostAdapter = PostAdapter(ArrayList())

}