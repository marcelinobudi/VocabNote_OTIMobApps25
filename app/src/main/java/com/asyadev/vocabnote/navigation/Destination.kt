package com.asyadev.vocabnote.navigation

import android.media.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.VectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import com.asyadev.vocabnote.R

enum class Destination(
    val route: String,
    val label: String,
    val icon: Int,
    val contentDescription: String? = null,
    val isBottomBar: Boolean = false
) {
    QUIZ("quiz", "Kuis", R.drawable.ic_quiz_24, "Kuis", true),
    VOCABULARY_LIST("vocabulary_list", "Vocabulary", R.drawable.ic_dictionary_24, "Daftar Vocabulary", true),
    ADD_VOCABULARY("add_vocabulary", "Tambah", R.drawable.ic_add_24, "Tambah Vocabulary", true),
    EDIT_VOCABULARY("edit_vocabulary", "", R.drawable.edit_32dp)

}