package com.example.news.pagination

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.news.model.Article
import com.example.news.service.ApiService
import com.example.news.service.Request

class PaginationSource : PagingSource<Int, Article>() {
    override fun getRefreshKey(state: PagingState<Int, Article>) = 1

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val page = params.key ?: 1
        val pageSize = params.loadSize ?: 1
        val prevKey = if (page == 1) null else page - 1

        val response = Request.getNews2(page, pageSize)
        return LoadResult.Page(
            data = response.articles,
            prevKey = prevKey,
            nextKey = page.plus(1)
        )
    }
}