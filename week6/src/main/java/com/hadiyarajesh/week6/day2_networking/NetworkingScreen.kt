package com.hadiyarajesh.week6.day2_networking

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withLink
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NetworkingScreen(
    viewModel: NetworkingViewModel = hiltViewModel()
) {
    val viewState by viewModel.viewState.collectAsState()
    val uriHandler = LocalUriHandler.current

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "NetworkingScreen") })
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            when (viewState) {
                NetworkingScreenViewState.Loading -> {
                    LoadingIndicator(
                        modifier = Modifier.fillMaxSize()
                    )
                }

                is NetworkingScreenViewState.Success -> {
                    val photos = (viewState as NetworkingScreenViewState.Success).photos

                    PhotoList(
                        modifier = Modifier.weight(1f),
                        photos = photos,
                        onClick = { url ->
                            uriHandler.openUri(url)
                        }
                    )
                }

                is NetworkingScreenViewState.Error -> {
                    val errorMessage = (viewState as NetworkingScreenViewState.Error).message

                    ErrorIndicator(
                        modifier = Modifier.fillMaxSize(),
                        message = errorMessage
                    )
                }

                NetworkingScreenViewState.Initial -> {}
            }
        }
    }
}

@Composable
private fun PhotoList(
    modifier: Modifier = Modifier,
    photos: List<Photo>,
    onClick: (String) -> Unit
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(photos) { photo ->
            PhotoItem(
                modifier = Modifier.fillMaxSize(),
                photo = photo,
                onClick = onClick
            )
        }
    }
}

@Composable
private fun PhotoItem(
    modifier: Modifier = Modifier,
    photo: Photo,
    onClick: (String) -> Unit
) {
    val idAnnotatedString = buildAnnotatedString {
        withStyle(style = SpanStyle(fontWeight = FontWeight.SemiBold)) {
            append("ID:")
        }

        append(photo.id.toString())
    }

    val titleAnnotatedString = buildAnnotatedString {
        withStyle(style = SpanStyle(fontWeight = FontWeight.SemiBold)) {
            append("Title: ")
        }

        append(photo.title)
    }

    val urlAnnotatedString = buildAnnotatedString {
        withStyle(style = SpanStyle(fontWeight = FontWeight.SemiBold)) {
            append("URL: ")
        }

        val link = LinkAnnotation.Url(
            photo.url,
            TextLinkStyles(
                SpanStyle(
                    color = Color.Blue,
                    textDecoration = TextDecoration.Underline
                )
            )
        ) {
            val url = (it as LinkAnnotation.Url).url
            onClick(url)
        }

        withLink(link) { append(photo.url) }
    }

    Card(modifier = modifier) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            Text(text = idAnnotatedString)
            Text(text = titleAnnotatedString)
            Text(
                text = urlAnnotatedString
            )
        }
    }
}
