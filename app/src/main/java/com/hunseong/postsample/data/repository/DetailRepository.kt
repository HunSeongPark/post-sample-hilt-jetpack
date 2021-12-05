package com.hunseong.postsample.data.repository

import com.hunseong.postsample.data.db.PostDao
import com.hunseong.postsample.data.model.Post
import com.hunseong.postsample.data.model.PostInfo
import com.hunseong.postsample.data.model.Result
import com.hunseong.postsample.data.network.CommentApiService
import com.hunseong.postsample.data.network.UserApiService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

class DetailRepository @Inject constructor(
    private val postDao: PostDao,
    private val commentApiService: CommentApiService,
    private val userApiService: UserApiService,
    private val ioDispatcher: CoroutineDispatcher,
) : Repository {

    fun getPostExist(id: Long) = postDao.getPostExist(id)

    fun getInfo(postId: Long, userId: Long) = flow {
        emit(Result.Loading)
        var postInfo: PostInfo
        withContext(ioDispatcher) {
            val comments = async { commentApiService.getComments(postId) }
            val user = async { userApiService.getUser(userId) }
            postInfo = PostInfo(comments.await(), user.await())
        }
        emit(Result.Success(postInfo))
    }.catch { e -> Result.Error(e) }

    suspend fun insertPost(post: Post) = postDao.insertPost(post)

    suspend fun deletePost(post: Post) = postDao.deletePost(post)

}