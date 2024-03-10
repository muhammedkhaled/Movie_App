package com.mkhaled.movieapp.detailsScreen.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import com.mkhaled.movieapp.R
import kotlin.math.roundToInt
import kotlin.math.roundToLong

@Composable
fun DetailsScreen(
    title: String,
    overview: String,
    image: String,
    releaseDate: String,
    voteAverage: Float,
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Card(
            colors = CardDefaults.cardColors(Color.Transparent),
            shape = RoundedCornerShape(bottomStart = 60.dp),
            elevation = CardDefaults.cardElevation(0.dp),
            modifier = Modifier
                .fillMaxWidth()
        ) {
            // Card content
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(image.replace("-", "/"))
                    .crossfade(true)
                    .scale(Scale.FILL)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .height(350.dp)
                    .fillMaxWidth()
            )
        }

        Spacer(modifier = Modifier.height(18.dp))
        Row(
            modifier = Modifier
                .padding(start = 20.dp, end = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = releaseDate, fontSize = 14.sp, modifier = Modifier.weight(1f))
            Image(
                painter = painterResource(id = R.drawable.baseline_star_24),
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(text = "%.1f".format(voteAverage), fontSize = 16.sp)
        }
        Spacer(modifier = Modifier.height(12.dp))
        Text(text = title, fontSize = 32.sp, modifier = Modifier.padding(start = 20.dp))
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = overview,
            fontSize = 18.sp,
            modifier = Modifier.padding(start = 20.dp, end = 20.dp)
        )

    }
}