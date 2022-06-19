package com.mvvmexample.nearbyplaces.ui.screen.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.flow.Flow

@Composable
fun <T : Any> BoxScope.LazyColumnWithPaging(
    pagingFlowResult: Flow<PagingData<T>>,
    onRetryFailure: (error: Throwable, retryFunc: () -> Unit) -> Unit,
    onRetry: () -> Unit,
    onRefresh: (() -> Unit)? = null,
    isLoading: Boolean = false,
    emptyMessage: @Composable () -> Unit,
    itemContent: LazyListScope.(LazyPagingItems<T>) -> Unit
) {
    val pagingItems = pagingFlowResult.collectAsLazyPagingItems()

    val refreshLoadState = pagingItems.loadState.refresh
    LaunchedEffect(refreshLoadState, pagingItems) {
        if (refreshLoadState is LoadState.Error) {
            onRetryFailure(refreshLoadState.error) { pagingItems.retry() }
        }
    }

    val hasError = pagingItems.loadState.mediator?.refresh is LoadState.Error
    val pagingIsLoading =
        pagingItems.loadState.prepend is LoadState.Loading ||
                pagingItems.loadState.append is LoadState.Loading ||
                pagingItems.loadState.refresh is LoadState.Loading

    val listState: LazyListState = rememberLazyListState()


    if (isLoading || pagingIsLoading && !hasError) {
        LinearProgressIndicator(
            Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter)
        )
    }

    SwipeRefresh(
        state = rememberSwipeRefreshState(false),
        swipeEnabled = hasError || (!pagingIsLoading && onRefresh != null),
        modifier = Modifier.fillMaxSize(),
        onRefresh = { if (hasError) onRetry() else onRefresh?.invoke() },
    ) {
        if (hasError) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                Text(text = "we have some errors")
            }
        } else {
            if (pagingItems.itemCount > 0) {
                LazyColumn(
                    state = listState,
                    content = { itemContent(pagingItems) }
                )
            } else if (!pagingIsLoading) {
                emptyMessage()
            }
        }
    }
}