package com.compose.presentation.notes

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.compose.R
import com.compose.domain.model.Note
import com.compose.presentation.MainViewModel
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.setValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.window.SecureFlagPolicy
import com.compose.presentation.ui.theme.*

@Composable
fun NotesScreen(viewModel: MainViewModel) {
    viewModel.notesState.loading?.let {
        NotesLoading()
    }
    viewModel.notesState.success?.let {
        NotesSuccess(viewModel)
    }
    viewModel.notesState.error?.let {
        viewModel.notesState.message?.let { safeMessage ->
            NotesError(safeMessage)
        }
    }
    DeleteConfirmationDialog(viewModel)
}

@Composable
private fun NotesLoading() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        CircularProgressIndicator()
    }
}

@Composable
private fun NotesSuccess(viewModel: MainViewModel) {
    val notes = viewModel.notesState.notes ?: listOf()
    val filteredNotes = arrayListOf<Note>()
    for (item in notes) {
        if (item.toString().contains(viewModel.searchQuery)) {
            filteredNotes.add(item)
        }
    }
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color5),
        ) {
            Text(
                text = stringResource(id = R.string.notes),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 16.dp),
                color = Color2,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
            )
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 32.dp,
                        end = 32.dp,
                        top = 16.dp,
                        bottom = 16.dp,
                    )
                    .border(
                        width = 1.dp,
                        color = Color6,
                        shape = CircleShape,
                    ),
                value = viewModel.searchQuery,
                onValueChange = {
                    viewModel.searchQuery = it
                },
                shape = CircleShape,
                placeholder = {
                    Text(text = stringResource(id = R.string.search_note))
                },
                colors = TextFieldDefaults.textFieldColors(
                    textColor = Color3,
                    backgroundColor = Color1,
                    disabledTextColor = Transparent,
                    focusedIndicatorColor = Transparent,
                    unfocusedIndicatorColor = Transparent,
                    disabledIndicatorColor = Transparent,
                    placeholderColor = Color6,
                ),
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_baseline_search_24),
                        contentDescription = null,
                        tint = Color3,
                    )
                },
                maxLines = 1,
                singleLine = true,
                textStyle = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    fontStyle = FontStyle.Normal,
                ),
            )
        }
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            itemsIndexed(filteredNotes) { index, item ->
                if (index == 0) {
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(16.dp)
                    )
                }
                NoteCard(item, viewModel)
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(16.dp)
                )
            }
        }
    }
}

@Composable
private fun NotesError(message: String) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = message,
            color = Color3,
            fontSize = 20.sp,
        )
    }
}

@Composable
private fun NoteCard(note: Note, viewModel: MainViewModel) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 16.dp,
                end = 16.dp,
            ),
        shape = RoundedCornerShape(16.dp),
        elevation = 2.dp,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .background(White)
                    .weight(1F),
            ) {
                Text(
                    text = note.subject,
                    color = Color3,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                )
                Spacer(
                    modifier = Modifier.height(8.dp)
                )
                Text(
                    text = note.content,
                    color = Color4,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                )
            }
            Card(
                modifier = Modifier
                    .padding(start = 16.dp)
                    .clip(CircleShape)
                    .clickable {
                        viewModel.noteToDelete = note
                    },
                shape = CircleShape,
                elevation = 0.dp,
                border = BorderStroke(1.dp, Color3),
            ) {
                Icon(
                    modifier = Modifier.padding(8.dp),
                    painter = painterResource(id = R.drawable.ic_baseline_close_24),
                    contentDescription = null,
                    tint = Color3,
                )
            }
        }
    }
}

@Composable
private fun DeleteConfirmationDialog(viewModel: MainViewModel) {
    viewModel.noteToDelete?.let { safeNote ->
        Dialog(
            onDismissRequest = { viewModel.noteToDelete = null },
            properties = DialogProperties(
                dismissOnBackPress = true,
                dismissOnClickOutside = true,
                securePolicy = SecureFlagPolicy.Inherit,
            ),
            content = {
                Card(
                    elevation = 8.dp,
                    shape = RoundedCornerShape(16.dp),
                    backgroundColor = White
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                top = 64.dp,
                                start = 32.dp,
                                end = 32.dp,
                                bottom = 32.dp,
                            ),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Text(
                            text = stringResource(id = R.string.delete_confirmation),
                            textAlign = TextAlign.Center,
                            color = Color4,
                            fontSize = 18.sp,
                        )
                        Row(
                            modifier = Modifier,
                        ) {
                            Button(
                                onClick = {
                                    viewModel.noteToDelete = null
                                },
                                content = {
                                    Text(
                                        text = stringResource(id = R.string.cancel),
                                        modifier = Modifier
                                            .padding(
                                                top = 8.dp,
                                                bottom = 8.dp,
                                                start = 12.dp,
                                                end = 12.dp,
                                            ),
                                        color = White
                                    )
                                },
                                modifier = Modifier.padding(top = 32.dp, end = 16.dp),
                                shape = RoundedCornerShape(16.dp),
                                colors = ButtonDefaults.buttonColors(backgroundColor = Color6)
                            )
                            Button(
                                onClick = {
                                    viewModel.deleteNote(safeNote)
                                    viewModel.noteToDelete = null
                                },
                                content = {
                                    Text(
                                        text = stringResource(id = R.string.delete),
                                        modifier = Modifier.padding(
                                            top = 8.dp,
                                            bottom = 8.dp,
                                            start = 12.dp,
                                            end = 12.dp
                                        )
                                    )
                                },
                                modifier = Modifier.padding(top = 32.dp, start = 16.dp),
                                shape = RoundedCornerShape(16.dp),
                            )
                        }
                    }
                }
            },
        )
    }
}