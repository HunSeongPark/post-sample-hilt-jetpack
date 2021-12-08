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
        // 새로운 CoroutineScope에서 나온 결과를 wrapping 할 PostInfo 객체
        var postInfo: PostInfo

        // ioDispatcher : DI(Hilt)를 통해 외부에서 주입받은 Dispatchers.IO
        // Dispatchers.IO context를 사용하는 새로운 CoroutineScope 생성
        withContext(ioDispatcher) {
            // async를 통해 두 api 통신을 비동기적으로 수행
            val comments = async { commentApiService.getComments(postId) }
            val user = async { userApiService.getUser(userId) }

            // await을 통해 두 결과가 모두 나왔을 때 postInfo에 해당 값 저장
            postInfo = PostInfo(comments.await(), user.await())
        }
        emit(Result.Success(postInfo))
    }.catch { e -> Result.Error(e) }

    suspend fun insertPost(post: Post) = postDao.insertPost(post)

    suspend fun deletePost(post: Post) = postDao.deletePost(post)

}