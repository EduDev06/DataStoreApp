package com.example.datastoreapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material.Surface
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.datastoreapp.R
import com.example.datastoreapp.ui.theme.DataStoreAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel = hiltViewModel<MyViewModel>()
            MyApp(viewModel = viewModel)
        }
    }
}

@Composable
fun MyApp(modifier: Modifier = Modifier, viewModel: MyViewModel) {
    val state = viewModel.state
    DataStoreAppTheme(darkTheme = state.darkThemeValue) {
        Surface {
            Column(
                modifier = modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LabelledSwitch(
                    isSwitchChecked = state.darkThemeValue,
                    label = stringResource(id = R.string.dark_theme_enabled),
                    onCheckedChange = { viewModel.onEvent(MyEvent.SelectedDarkThemeValue(it)) },
                    saveDarkThemeValue = { viewModel.onEvent(MyEvent.SaveDarkThemeValue(it)) }
                )
            }
        }
    }
}

@Composable
fun LabelledSwitch(
    modifier: Modifier = Modifier,
    isSwitchChecked: Boolean,
    label: String,
    onCheckedChange: (Boolean) -> Unit,
    saveDarkThemeValue: (Boolean) -> Unit,
) {
    Surface {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .height(36.dp)
                .toggleable(
                    value = isSwitchChecked,
                    onValueChange = {
                        onCheckedChange(it)
                        saveDarkThemeValue(it)
                    },
                    role = Role.Switch,
                )
                .padding(horizontal = 8.dp)
        ) {
            Text(
                text = label,
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(end = 16.dp)
            )
            Switch(
                checked = isSwitchChecked,
                onCheckedChange = null,
                modifier = Modifier.align(Alignment.CenterEnd)
            )
        }
    }
}