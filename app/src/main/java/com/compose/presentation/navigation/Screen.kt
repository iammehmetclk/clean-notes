package com.compose.presentation.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.compose.R

sealed class Screen(
    @StringRes val title: Int,
    @DrawableRes val icon: Int,
    val route: String,
) {
    object Notes: Screen(title = R.string.notes, icon = R.drawable.ic_baseline_description_24, route = "NOTES")
    object AddNote: Screen(title = R.string.add_note, icon = R.drawable.ic_baseline_add_24, route = "ADD_NOTE")
    object Info: Screen(title = R.string.info, icon = R.drawable.ic_baseline_info_24, route = "INFO")
}