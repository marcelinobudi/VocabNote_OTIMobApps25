package com.asyadev.vocabnote.ui

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.asyadev.vocabnote.R
import com.asyadev.vocabnote.database.Vocabulary
import com.asyadev.vocabnote.ui.theme.VocabNoteTheme
import com.asyadev.vocabnote.viewmodel.VocabularyViewModel

@Composable
fun VocabularyMenu(viewModel: VocabularyViewModel, modifier: Modifier = Modifier) {
    val vocabularies = viewModel.vocabularyList.observeAsState()
    val vocabulary = vocabularies.value ?: listOf()
    val vocabularyCard = remember { mutableStateOf<Vocabulary?>(null) }
    try {
        viewModel.getVocabularyList()
        Log.d("Vocabulary Menu", "Sukses")
    } catch (e: Exception){
        Log.d("Vocabulary Menu", e.message.toString())
    }
    if(vocabularyCard.value != null){
        Column(
            Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card {
                Column(
                    Modifier.padding(12.dp)
                ) {
                    Text(
                        text = vocabularyCard.value?.word + " - " + vocabularyCard.value?.pronounciation,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = vocabularyCard.value?.translation + ""
                    )
                }
                Column(Modifier.padding(12.dp)) {
                    
                }
                Row {

                }
            }
        }
    } else {
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier.fillMaxWidth()
        ) {
            items(1) {
                Spacer(Modifier.height(12.dp))
                Text(
                    text = "Daftar Kosakata",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(27.dp))
            }
            items(vocabulary.size) {
                VocabularyCard(
                    vocabulary = vocabulary[it],
                    onClick = {
                        vocabularyCard.value = vocabulary[it]
                    },
                    series = vocabulary.size - it
                )
            }
        }
    }
}

@Composable
fun VocabularyCard(
    series: Int,
    vocabulary: Vocabulary,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
    ) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp,
        ),
        colors = CardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
            contentColor = MaterialTheme.colorScheme.onSecondaryContainer,
            disabledContentColor = MaterialTheme.colorScheme.onSecondaryContainer,
            disabledContainerColor = MaterialTheme.colorScheme.secondaryContainer,
        ),
//        colors = CardColors(
//            containerColor = MaterialTheme.colorScheme.primaryContainer,
//            contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
//            disabledContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
//            disabledContainerColor = MaterialTheme.colorScheme.primaryContainer,
//        ),
        modifier = Modifier
            .size(width = 364.dp, height = 80.dp)
    ) {
        Row(
            Modifier.fillMaxSize()
        ) {
            Column(
                Modifier.size(80.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(
                    Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.onTertiaryContainer),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = series.toString(), color = MaterialTheme.colorScheme.tertiaryContainer)
                }
            }
            Column(
                Modifier
                    .fillMaxHeight()
                    .width(204.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = vocabulary.word,
                    fontWeight = FontWeight.Bold
                )
                Text(text = vocabulary.translation)
            }
            Column(
                Modifier
                    .size(80.dp)
                    .clickable {
                        onClick()
                    },
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(R.drawable.edit_32dp),
                    contentDescription = "Edit Kosakata",
                    modifier = Modifier.width(32.dp)
                )
            }
        }
    }
    Spacer(Modifier.height(16.dp))
}