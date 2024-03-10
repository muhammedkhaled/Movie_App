//package com.mkhaled.movieapp.movieScreen.presentation
//
//import android.net.Uri
//import android.widget.Toast
//import androidx.compose.foundation.ExperimentalFoundationApi
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.PaddingValues
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.imePadding
//import androidx.compose.foundation.layout.navigationBarsPadding
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.pager.HorizontalPager
//import androidx.compose.foundation.pager.PagerState
//import androidx.compose.foundation.rememberScrollState
//import androidx.compose.foundation.text.KeyboardActions
//import androidx.compose.foundation.text.KeyboardOptions
//import androidx.compose.foundation.verticalScroll
//import androidx.compose.material3.Button
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Shapes
//import androidx.compose.material3.Text
//import androidx.compose.material3.TextField
//import androidx.compose.material3.TextFieldDefaults
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.LaunchedEffect
//import androidx.compose.runtime.rememberCoroutineScope
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.focus.FocusDirection
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.platform.LocalFocusManager
//import androidx.compose.ui.text.input.ImeAction
//import androidx.compose.ui.unit.dp
//import coil.compose.AsyncImage
//import coil.request.ImageRequest
//import kotlinx.coroutines.launch
//
//@OptIn(ExperimentalFoundationApi::class)
//@Composable
//fun WriteContent(
//    uiState: UiState,
//    pagerState: PagerState,
//    title: String,
//    description: String,
//    paddingValues: PaddingValues
//) {
//    val scrollState = rememberScrollState()
//    val scope = rememberCoroutineScope()
//    val context = LocalContext.current
//    val focusManager = LocalFocusManager.current
//
//    LaunchedEffect(key1 = scrollState.maxValue) {
//        scrollState.scrollTo(scrollState.maxValue)
//    }
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .imePadding()
//            .navigationBarsPadding()
//            .padding(top = paddingValues.calculateTopPadding())
//            .padding(bottom = 24.dp)
//            .padding(horizontal = 24.dp),
//        verticalArrangement = Arrangement.SpaceBetween
//    ) {
//        Column(
//            modifier = Modifier
//                .weight(1f)
//                .fillMaxWidth()
//                .verticalScroll(state = scrollState)
//        ) {
//            Spacer(modifier = Modifier.height(30.dp))
//            HorizontalPager(state = pagerState) { page ->
//                Box(
//                    modifier = Modifier.fillMaxWidth(),
//                    contentAlignment = Alignment.Center
//                ) {
//                    AsyncImage(
//                        modifier = Modifier.size(120.dp),
//                        model = ImageRequest.Builder(LocalContext.current)
//                            .data(Mood.values()[page].icon)
//                            .crossfade(true)
//                            .build(),
//                        contentDescription = "Mood Image"
//                    )
//                }
//            }
//
///*
//        Column(verticalArrangement = Arrangement.Bottom) {
//            Spacer(modifier = Modifier.height(12.dp))
//            GalleryUploader(
//                galleryState = galleryState,
//                onAddClicked = { focusManager.clearFocus() },
//                onImageSelect = onImageSelect,
//                onImageClicked = onImageClicked
//            )
//            Spacer(modifier = Modifier.height(12.dp))
//            Button(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(54.dp),
//                onClick = {
//                    if (uiState.title.isNotEmpty() && uiState.description.isNotEmpty()) {
//                        onSaveClicked(
//                            Diary().apply {
//                                this.title = uiState.title
//                                this.description = uiState.description
//                                this.images = galleryState.images.map { it.remoteImagePath }.toRealmList()
//                            }
//                        )
//                    } else {
//                        Toast.makeText(
//                            context,
//                            "Fields cannot be empty.",
//                            Toast.LENGTH_SHORT
//                        ).show()
//                    }
//                },
//                shape = Shapes().small
//            ) {
//                Text(text = "Save")
//            }
//        }
//*/
//    }
//}