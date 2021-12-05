package com.hunseong.postsample.di

import com.hunseong.postsample.data.db.PostDao
import com.hunseong.postsample.data.db.UserDao
import com.hunseong.postsample.data.network.CommentApiService
import com.hunseong.postsample.data.network.PostApiService
import com.hunseong.postsample.data.network.UserApiService
import com.hunseong.postsample.data.repository.DetailRepository
import com.hunseong.postsample.data.repository.FavoriteRepository
import com.hunseong.postsample.data.repository.PostRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher

@InstallIn(ViewModelComponent::class)
@Module
object RepositoryModule {

    @Provides
    @ViewModelScoped
    fun providePostRepository(postApiService: PostApiService): PostRepository {
        return PostRepository(postApiService)
    }

    @Provides
    @ViewModelScoped
    fun provideDetailRepository(
        postDao: PostDao,
        commentApiService: CommentApiService,
        userApiService: UserApiService,
        ioDispatcher: CoroutineDispatcher
    ): DetailRepository {
        return DetailRepository(postDao, commentApiService, userApiService, ioDispatcher)
    }

    @Provides
    @ViewModelScoped
    fun provideFavoriteRepository(
        postDao: PostDao,
        userDao: UserDao
    ): FavoriteRepository {
        return FavoriteRepository(postDao, userDao)
    }
}