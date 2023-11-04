package com.ammar.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ammar.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArtWorkLayout()
                }
            }
        }
    }
}

val cardWidth = 250.dp
val imageHeight = 500.dp

@Composable
fun ArtWorkLayout(){
    val artWorks = arrayOf(
        ArtWork(R.drawable.golden_stro, R.string.golden_strawberry, R.string.unknown),
        ArtWork(R.drawable.light_stro, R.string.broken_glass, R.string.unknown),
        ArtWork(R.drawable.gelly_fish, R.string.gilly_fish, R.string.unknown),
        ArtWork(R.drawable.gra_35_shape, R.string.gra_3d, R.string.unknown),
        ArtWork(R.drawable.gra_3d, R.string.gra_3d, R.string.unknown)
    )
    var index by remember{ mutableStateOf(0) }
    val (imageId, titleId, artistId) = artWorks[index]

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
            .statusBarsPadding()
            .padding(40.dp)
            .safeDrawingPadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        ArtWorkImage(imageId)
        Spacer(modifier = Modifier.height(64.dp))
        Column(
            modifier = Modifier
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Bottom,
        ) {
            TitleAndArtistTextRow(titleId, artistId)
            NavigationButtonsRow(
                { index = ++index % artWorks.size },
                { index = mMod(index, artWorks.size) }
            )
        }
    }
}

@Composable
fun ArtWorkImage(
    @DrawableRes id: Int,
    modifier: Modifier = Modifier
){
    Box(
        modifier = modifier
            .width(cardWidth)
            .height(imageHeight)
            .shadow(2.dp)
    ) {
        Image(
            modifier = modifier
                .fillMaxSize()
                .padding(32.dp),
            painter = painterResource(id = id),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun TitleAndArtistTextRow(
    @StringRes titleId: Int,
    @StringRes artistId: Int,
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = modifier
                .width(cardWidth)
        ) {
            Text(
                modifier = modifier
                    .fillMaxWidth()
                    .align(Alignment.Start)
                    .padding(8.dp),
                text = stringResource(id = titleId),
                textAlign = TextAlign.Start,
                fontSize = 15.sp,
                style = MaterialTheme.typography.titleSmall
            )
            Text(
                modifier = modifier
                    .fillMaxWidth()
                    .align(Alignment.End)
                    .padding(8.dp),
                text = stringResource(id = artistId),
                textAlign = TextAlign.Start,
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.bodySmall
            )
        }
        Spacer(modifier = modifier.height(32.dp))
    }
}

@Composable
fun NavigationButtonsRow(
    onNextButtonClicked: () -> Unit,
    onPreviousButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
){
    Box(
        modifier = modifier.fillMaxWidth()
    ){
        Button(
            modifier = modifier
                .width(150.dp)
                .align(Alignment.BottomStart),
            onClick = onPreviousButtonClicked
        ) {
            Text(text = stringResource(id = R.string.previous))
        }

        Spacer(
            modifier = modifier
                .width(16.dp)
                .align(Alignment.BottomCenter)
        )

        Button(
            modifier = modifier
                .width(150.dp)
                .align(Alignment.BottomEnd),
            onClick = onNextButtonClicked
        ) {
            Text(text = stringResource(id = R.string.next))
        }
    }
}

private fun mMod(value: Int, m: Int): Int {
    val v = (value - 1) % m
    return if(v<0) v + m else v
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    ArtSpaceTheme {
        ArtWorkLayout()
    }
}