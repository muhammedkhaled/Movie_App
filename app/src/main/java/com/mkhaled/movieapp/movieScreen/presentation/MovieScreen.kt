package com.mkhaled.movieapp.movieScreen.presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.absoluteValue
import androidx.compose.ui.util.lerp
import androidx.lifecycle.Lifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import com.mkhaled.movieapp.core.presentation.ui.ComposableLifecycle
import com.mkhaled.movieapp.movieScreen.domain.model.MovieUIData

@Composable
fun MovieScreen(
    state: UiState,
    onEvent: (UiEvent) -> Unit,
    onClick: (String, String, String, String, Float) -> Unit,
) {
    ComposableLifecycle { source, event ->
        if (event == Lifecycle.Event.ON_CREATE) {
            onEvent.invoke(UiEvent.GetData)
        }
    }
    Column(modifier = Modifier.fillMaxSize()) {
        HorizontalPagerList(state, onClick = onClick)
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HorizontalPagerList(
    state: UiState,
    modifier: Modifier = Modifier,
    onClick: (String, String, String, String, Float) -> Unit,
) {
    val pagerState = rememberPagerState(pageCount = {
        state.moviesList.size
    })
    HorizontalPager(
        state = pagerState,
        contentPadding = PaddingValues(horizontal = 80.dp),
        modifier = modifier
            .fillMaxSize()
    ) { page ->
        Column(modifier = Modifier.fillMaxSize()) {
            Spacer(modifier = Modifier.padding(top = 60.dp))
            Card(colors = CardDefaults.cardColors(Color.Transparent),
                shape = RoundedCornerShape(20.dp),
                elevation = CardDefaults.cardElevation(0.dp),
                modifier = Modifier
                    .weight(1f)
                    .clickable {
                        val movie = state.moviesList[page]
                        onClick(
                            movie.title,
                            movie.overview,
                            movie.image.replace("/", "-"),
                            movie.releaseDate,
                            movie.voteAverage.toFloat()
                        )
                    }
                    .fillMaxWidth()
                    .graphicsLayer {
                        val pageOffset =
                            pagerState.calculateCurrentOffsetForPage(page).absoluteValue
                        lerp(
                            start = 0.85f, stop = 1f, fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        ).also { scale ->
                            scaleX = scale
                            scaleY = scale
                        }
                        alpha = lerp(
                            start = 0.5f, stop = 1f, fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        )
                    }
            ) {
                // Card content
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(state.moviesList[page].image)
                        .crossfade(true)
                        .scale(Scale.FILL)
                        .build(),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .offset {
                            // Calculate the offset for the current page from the
                            // scroll position
                            val pageOffset = pagerState.calculateCurrentOffsetForPage(page)
                            // Then use it as a multiplier to apply an offset
                            IntOffset(
                                x = (40.dp * pageOffset).roundToPx(),
                                y = 0,
                            )
                        }
                        .fillMaxSize()
                )
            }
            Spacer(modifier = Modifier.padding(top = 10.dp))
            Text(
                text = state.moviesList[page].title,
                fontSize = 18.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 18.dp, bottom = 18.dp)
                    .wrapContentSize()
            )
            Spacer(modifier = Modifier.padding(top = 60.dp))
        }

    }
}

val imageUrls = listOf(
    "https://cdn.pixabay.com/photo/2023/04/06/02/54/bird-7902724_960_720.jpg",
    "https://cdn.pixabay.com/photo/2023/04/10/20/41/bird-7914702_960_720.jpg",
    "https://cdn.pixabay.com/photo/2024/02/24/10/31/norway-8593725_960_720.jpg",
    "https://cdn.pixabay.com/photo/2017/01/14/12/59/iceland-1979445_960_720.jpg",
    "https://cdn.pixabay.com/photo/2024/02/24/10/31/norway-8593725_960_720.jpg"
)

//@Preview
//@Composable
//fun HorizontalPagerListPrev() {
//    Box(modifier = Modifier.fillMaxSize()) {
//        HorizontalPagerList(
//            UiState(false, imageUrls.map { MovieUIData(0, "", "", it) }),
//            onClick = {  "" "", "", "", 0 }
//        )
//    }
//}

// To get scrolled offset from snap position
@OptIn(ExperimentalFoundationApi::class)
fun PagerState.calculateCurrentOffsetForPage(page: Int): Float {
    return (currentPage - page) + currentPageOffsetFraction
}