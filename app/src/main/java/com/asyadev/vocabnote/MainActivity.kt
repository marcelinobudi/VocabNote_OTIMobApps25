package com.asyadev.vocabnote

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.asyadev.vocabnote.database.AppDatabase
import com.asyadev.vocabnote.navigation.AppNavHost
import com.asyadev.vocabnote.navigation.Destination
import com.asyadev.vocabnote.ui.theme.VocabNoteTheme
import com.asyadev.vocabnote.viewmodel.VocabularyViewModel
import com.asyadev.vocabnote.viewmodel.VocabularyViewModelFactory

class MainActivity : ComponentActivity() {
    private val vocabularyViewModel: VocabularyViewModel by viewModels {
        VocabularyViewModelFactory((application as VocabularyApplication).repository)
    }
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            VocabNoteTheme {
                val navController = rememberNavController()
                val startDestination = Destination.VOCABULARY_LIST
                var selectedDestination by rememberSaveable { mutableIntStateOf(startDestination.ordinal) }

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = MaterialTheme.colorScheme.primaryContainer,
                                titleContentColor = MaterialTheme.colorScheme.primary,
                            ),
                            title = {
                                Image(
                                    painter = painterResource(R.drawable.title_app),
                                    contentDescription = "App Title",
                                    modifier = Modifier
                                        .width(120.dp)
                                        .height(20.dp)
                                )
                            },
                            actions = {
                                IconButton(onClick = {
                                    // Pindah Ke Pengaturan
                                }) {
                                    Image(
                                        painter = painterResource(R.drawable.settings_24dp),
                                        contentDescription = "Settings",
                                        modifier = Modifier
                                            .width(24.dp)
                                            .height(24.dp)
                                    )
                                }
                            }
                        )
                    },
                    bottomBar = {
                        NavigationBar(windowInsets = NavigationBarDefaults.windowInsets) {
                            Destination.entries.forEachIndexed { index, destination ->
                                if(destination.isBottomBar) {
                                    NavigationBarItem(
                                        selected = selectedDestination == index,
                                        onClick = {
                                            navController.navigate(route = destination.route)
                                            selectedDestination = index
                                        },
                                        icon = {
                                            Image(
                                                painter = painterResource(id = destination.icon),
                                                contentDescription = destination.contentDescription
                                            )
                                        },
                                        label = { Text(destination.label) }
                                    )
                                }
                            }
                        }
                    }
                ) { contentPadding ->

                    AppNavHost(navController, startDestination, vocabularyViewModel, modifier = Modifier.padding(contentPadding))
                }
            }
        }
    }
}

