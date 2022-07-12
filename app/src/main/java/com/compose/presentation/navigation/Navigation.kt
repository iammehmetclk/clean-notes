package com.compose.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.compose.presentation.MainViewModel
import com.compose.presentation.add_note.AddNoteScreen
import com.compose.presentation.notes.NotesScreen
import com.compose.presentation.settings.SettingsScreen
import com.compose.presentation.ui.theme.*

@Composable
fun Navigation(viewModel: MainViewModel) {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomNavigation(
                backgroundColor = Color5,
            ) {
                val items = listOf(Screen.Notes, Screen.AddNote, Screen.Info)
                val backStackEntry by navController.currentBackStackEntryAsState()
                items.forEach { item ->
                    val selected = item.route == backStackEntry?.destination?.route
                    BottomNavigationItem(
                        icon = { Icon( painter = painterResource(id = item.icon), contentDescription = null) },
                        label = { Text(stringResource(item.title)) },
                        selected = selected,
                        selectedContentColor = Color2,
                        unselectedContentColor = Color4,
                        onClick = {
                            navController.navigate(item.route) {
                                popUpTo(backStackEntry?.destination?.route ?: Screen.Notes.route) {
                                    saveState = true
                                    inclusive = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding: PaddingValues ->
        NavHost(navController, startDestination = Screen.Notes.route, Modifier.padding(innerPadding)) {
            composable(Screen.Notes.route) {
                NotesScreen(viewModel)
            }
            composable(Screen.AddNote.route) {
                AddNoteScreen(viewModel)
            }
            composable(Screen.Info.route) {
                SettingsScreen()
            }
        }
    }
}