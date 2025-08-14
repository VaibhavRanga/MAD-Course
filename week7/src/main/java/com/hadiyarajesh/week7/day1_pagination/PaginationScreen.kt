package com.hadiyarajesh.week7.day1_pagination

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil3.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaginationScreen(
    viewModel: PaginationViewModel = hiltViewModel()
) {
//    val repositories by viewModel.repositories.collectAsState()
    val repositories = viewModel.repositories.collectAsLazyPagingItems()
    val uriHandler = LocalUriHandler.current

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Pagination Screen") })
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            RepositoryList(
                modifier = Modifier.fillMaxSize(),
                repositories = repositories,
                onUserProfileClick = { repositoryOwner ->
                    uriHandler.openUri(repositoryOwner.profileUrl)
                },
                onRepositoryUrlClick = { repository ->
                    uriHandler.openUri(repository.repositoryUrl)
                }
            )
        }
    }
}

@Composable
private fun RepositoryList(
    modifier: Modifier = Modifier,
    repositories: LazyPagingItems<Repository>,
    onUserProfileClick: (RepositoryOwner) -> Unit,
    onRepositoryUrlClick: (Repository) -> Unit
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(items = repositories) { repository ->
            RepositoryItem(
                modifier = Modifier.fillMaxWidth(),
                repository = repository,
                onUserProfileClick = onUserProfileClick,
                onRepositoryUrlClick = onRepositoryUrlClick
            )
        }

        repositories.apply {
            if (loadState.refresh is LoadState.Loading) {
                item {
                    LoadingIndicator(modifier = Modifier.fillParentMaxSize())
                }
            }

            if (loadState.refresh is LoadState.Error) {
                item {
                    val errorMessage =
                        (repositories.loadState.refresh as LoadState.Error).error.message
                            ?: "Something went wrong"

                    ErrorIndicatorWithRetry(
                        modifier = Modifier.fillParentMaxSize(),
                        message = errorMessage,
                        buttonText = "Refresh",
                        onButtonClick = { repositories.refresh() }
                    )
                }
            }

            if (loadState.append is LoadState.Loading) {
                item {
                    LoadingIndicator(modifier = Modifier.fillParentMaxWidth())
                }
            }

            if (loadState.append is LoadState.Error) {
                item {
                    val errorMessage =
                        (repositories.loadState.refresh as LoadState.Error).error.message
                            ?: "Something went wrong"

                    ErrorIndicatorWithRetry(
                        modifier = Modifier.fillParentMaxWidth(),
                        message = errorMessage,
                        buttonText = "Retry",
                        onButtonClick = { repositories.retry() }
                    )
                }
            }
        }
    }
}

@Composable
private fun RepositoryItem(
    modifier: Modifier = Modifier,
    repository: Repository,
    onUserProfileClick: (RepositoryOwner) -> Unit,
    onRepositoryUrlClick: (Repository) -> Unit
) {
    Card(modifier = modifier) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape)
                        .background(Color.Gray)
                        .border(1.dp, Color.Blue, CircleShape)
                        .clickable {
                            onUserProfileClick(repository.owner)
                        }
                ) {
                    AsyncImage(
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(CircleShape),
                        model = repository.owner.profilePicUrl,
                        contentDescription = repository.owner.username,
                        contentScale = ContentScale.Crop
                    )
                }

                Spacer(modifier = Modifier.width(8.dp))

                Row(
                    modifier = Modifier.weight(1f)
                ) {
                    Column {
                        Text(
                            text = repository.name,
                            modifier = Modifier.padding(bottom = 4.dp),
                            style = MaterialTheme.typography.titleMedium
                        )

                        repository.description?.let {
                            Text(
                                text = it,
                                modifier = Modifier.padding(bottom = 4.dp),
                                maxLines = 3,
                                overflow = TextOverflow.Ellipsis
                            )
                        }

                        Text(
                            modifier = Modifier.clickable { onRepositoryUrlClick(repository) },
                            text = repository.repositoryUrl,
                            style = MaterialTheme.typography.labelLarge,
                            color = Color.Blue,
                            textDecoration = TextDecoration.Underline
                        )
                    }
                }

                Spacer(modifier = Modifier.width(8.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(imageVector = Icons.Default.Star, contentDescription = null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = repository.numberOfStars.toString())
                }
            }
        }
    }
}

@Preview
@Composable
private fun RepositoryItemPreview() {
    RepositoryItem(
        modifier = Modifier.fillMaxWidth(),
        repository = Repository(
            id = 1,
            name = "Repository Name",
            owner = RepositoryOwner(
                id = 1,
                username = "hadiyarajesh",
                profilePicUrl = "",
                profileUrl = ""
            ),
            description = "Repository Description",
            repositoryUrl = "https://github.com/hadiyarajesh/repository",
            numberOfStars = 100
        ),
        onUserProfileClick = {},
        onRepositoryUrlClick = {}
    )
}