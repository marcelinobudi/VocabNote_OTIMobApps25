package com.asyadev.vocabnote.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.asyadev.vocabnote.ui.theme.VocabNoteTheme

@Composable
fun VocabularyMenu(modifier: Modifier = Modifier) {

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun VocabularyMenuPreview() {
    VocabNoteTheme {
        VocabularyMenu()
    }
}