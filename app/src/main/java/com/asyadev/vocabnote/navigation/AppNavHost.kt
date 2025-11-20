package com.asyadev.vocabnote.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.asyadev.vocabnote.database.Vocabulary
import com.asyadev.vocabnote.ui.AddVocabularyMenu
import com.asyadev.vocabnote.ui.EditVocabularyMenu
import com.asyadev.vocabnote.ui.QuizMenu
import com.asyadev.vocabnote.ui.VocabularyMenu
import com.asyadev.vocabnote.viewmodel.VocabularyViewModel

@Composable
fun AppNavHost(
    navController: NavHostController,
    startDestination: Destination,
    vocabularyViewModel: VocabularyViewModel,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController,
        startDestination = startDestination.route
    ) {
        Destination.entries.forEach { destination ->
            composable(destination.route) {
                when (destination) {
                    Destination.VOCABULARY_LIST -> VocabularyMenu(vocabularyViewModel, modifier)
                    Destination.ADD_VOCABULARY -> AddVocabularyMenu(vocabularyViewModel, modifier)
                    Destination.QUIZ -> QuizMenu(vocabularyViewModel, modifier)
                    Destination.EDIT_VOCABULARY -> EditVocabularyMenu(modifier)
                }
            }
        }
    }
}