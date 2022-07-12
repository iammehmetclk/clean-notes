package com.compose.presentation.add_note

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.window.SecureFlagPolicy
import com.compose.R
import com.compose.presentation.MainViewModel
import com.compose.presentation.ui.theme.Color4
import com.compose.presentation.ui.theme.White

@Composable
fun AddNoteScreen(viewModel: MainViewModel) {
    AddNoteContent(viewModel)
    AddNoteResultDialog(viewModel)
}

@Composable
private fun AddNoteContent(viewModel: MainViewModel) {
    if (viewModel.subject.isBlank() && viewModel.content.isBlank()) {
        LocalFocusManager.current.clearFocus(true)
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp, end = 24.dp, top = 16.dp),
        ) {
            Text(
                modifier = Modifier.align(Alignment.CenterStart),
                text = stringResource(id = R.string.add_note),
                fontSize = 20.sp,
                color = Color4,
                fontWeight = FontWeight.Bold
            )
            Button(
                onClick = {
                    if (viewModel.subject.isNotEmpty() && viewModel.content.isNotEmpty()) {
                        viewModel.insertNote()
                    }
                },
                content = {
                    Text(
                        text = stringResource(id = R.string.save),
                        modifier = Modifier.padding(
                            top = 4.dp,
                            bottom = 4.dp,
                            start = 12.dp,
                            end = 12.dp
                        )
                    )
                },
                modifier = Modifier.align(Alignment.CenterEnd),
                shape = RoundedCornerShape(16.dp),
            )
        }
        OutlinedTextField(
            value = viewModel.subject,
            onValueChange = { viewModel.subject = it },
            label = {
                Text(text = stringResource(id = R.string.subject))

            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 16.dp)
        )
        OutlinedTextField(
            value = viewModel.content,
            onValueChange = { viewModel.content = it },
            label = {
                Text(text = stringResource(id = R.string.content))
            },
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(
                    start = 16.dp,
                    end = 16.dp,
                    top = 16.dp,
                    bottom = 16.dp
                )
        )
    }
}

@Composable
private fun AddNoteResultDialog(viewModel: MainViewModel) {
    viewModel.addNoteState?.let { safeState ->
        Dialog(
            onDismissRequest = { },
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
                            text = safeState,
                            textAlign = TextAlign.Center,
                            color = Color4,
                            fontSize = 18.sp,
                        )
                        Button(
                            onClick = {
                                viewModel.addNoteState = null
                                viewModel.searchQuery = ""
                                viewModel.subject = ""
                                viewModel.content = ""
                            },
                            content = {
                                Text(
                                    text = stringResource(id = R.string.okay),
                                    modifier = Modifier.padding(
                                        top = 8.dp,
                                        bottom = 8.dp,
                                        start = 12.dp,
                                        end = 12.dp
                                    )
                                )
                            },
                            modifier = Modifier.padding(top = 32.dp),
                            shape = RoundedCornerShape(16.dp),
                        )
                    }
                }
            },
        )
    }
}