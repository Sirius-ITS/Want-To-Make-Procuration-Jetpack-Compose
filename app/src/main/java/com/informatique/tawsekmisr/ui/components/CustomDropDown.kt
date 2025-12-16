//package com.informatique.tawsekmisr.ui.components
//
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.layout.width
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.items
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.foundation.text.KeyboardOptions
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.rounded.ArrowDropDown
//import androidx.compose.material.icons.rounded.Check
//import androidx.compose.material.icons.rounded.Search
//import androidx.compose.material3.ExperimentalMaterial3Api
//import androidx.compose.material3.HorizontalDivider
//import androidx.compose.material3.Icon
//import androidx.compose.material3.ListItem
//import androidx.compose.material3.ListItemDefaults
//import androidx.compose.material3.ModalBottomSheet
//import androidx.compose.material3.OutlinedTextField
//import androidx.compose.material3.OutlinedTextFieldDefaults
//import androidx.compose.material3.Text
//import androidx.compose.material3.TextField
//import androidx.compose.material3.TextFieldDefaults
//import androidx.compose.material3.rememberModalBottomSheetState
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.rememberCoroutineScope
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.graphics.vector.ImageVector
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.text.input.ImeAction
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import com.informatique.tawsekmisr.ui.theme.LocalExtraColors
//import kotlinx.coroutines.launch
//
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun CustomDropdown(
//    label: String,
//    options: List<String>,
//    selectedOption: String?,
//    onOptionSelected: (String) -> Unit,
//    error: String? = null,
//    mandatory: Boolean = false,
//    enabled: Boolean = true,
//    leadingIcon: ImageVector? = null,
//    placeholder: String? = null
//) {
//    var showSheet by remember { mutableStateOf(false) }
//    var searchQuery by remember { mutableStateOf("") }
//    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
//    val coroutineScope = rememberCoroutineScope()
//    val extraColors = LocalExtraColors.current
//
//    val filteredOptions = options.filter {
//        it.contains(searchQuery, ignoreCase = true)
//    }
//
//    Column(modifier = Modifier.fillMaxWidth()) {
//        Box(
//            modifier = Modifier
//                .fillMaxWidth()
//                .clickable(enabled = enabled) {
//                    showSheet = true
//                }
//        ) {
//            OutlinedTextField(
//                value = selectedOption ?: "",
//                onValueChange = {},
//                shape = RoundedCornerShape(16.dp),
//                colors = OutlinedTextFieldDefaults.colors(
//                    focusedContainerColor = extraColors.cardBackground,
//                    unfocusedContainerColor = extraColors.cardBackground,
//                    disabledContainerColor = extraColors.cardBackground,
//                    focusedBorderColor = if (error != null) Color(0xFFE74C3C) else Color.Transparent,
//                    unfocusedBorderColor = Color.Transparent,
//                    disabledBorderColor = Color.Transparent,
//                    focusedTextColor = extraColors.textBlue,
//                    unfocusedTextColor = extraColors.textBlue,
//                    disabledTextColor = extraColors.textBlue
//                ),
//                readOnly = true,
//                enabled = false,
//                placeholder = {
//                    if (selectedOption == null && placeholder != null) {
//                        Text(
//                            text = placeholder,
//                            color = extraColors.textDarkGray,
//                            fontSize = 16.sp
//                        )
//                    }
//                },
//                leadingIcon = if (leadingIcon != null) {
//                    {
//                        Icon(
//                            imageVector = leadingIcon,
//                            contentDescription = null,
//                            tint = extraColors.iconDarkBlue,
//                            modifier = Modifier.size(24.dp)
//                        )
//                    }
//                } else null,
//                trailingIcon = {
//                    Icon(
//                        imageVector = Icons.Rounded.ArrowDropDown,
//                        contentDescription = "Dropdown Icon",
//                        tint = if (enabled) extraColors.textDarkGray
//                        else extraColors.textGray.copy(alpha = 0.3f)
//                    )
//                },
//                modifier = Modifier.fillMaxWidth(),
//                isError = error != null,
//                singleLine = true
//            )
//        }
//
//        if (error != null) {
//            Text(
//                text = error,
//                fontSize = 12.sp,
//                color = Color(0xFFE74C3C),
//                modifier = Modifier.padding(top = 4.dp, start = 4.dp)
//            )
//        }
//    }
//
//    if (showSheet) {
//        ModalBottomSheet(
//            onDismissRequest = {
//                showSheet = false
//                searchQuery = ""
//            },
//            sheetState = sheetState,
//            containerColor = extraColors.background
//        ) {
//            Column(modifier = Modifier.fillMaxWidth()) {
//                Row(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(horizontal = 16.dp, vertical = 8.dp),
//                    verticalAlignment = Alignment.CenterVertically
//                ) {
//                    if (leadingIcon != null) {
//                        Icon(
//                            imageVector = leadingIcon,
//                            contentDescription = null,
//                            tint = extraColors.iconDarkBlue,
//                            modifier = Modifier.size(24.dp)
//                        )
//                        Spacer(modifier = Modifier.width(12.dp))
//                    }
//                    Text(
//                        text = if (mandatory) "$label *" else label,
//                        fontSize = 18.sp,
//                        fontWeight = FontWeight.Bold,
//                        color = extraColors.white
//                    )
//                }
//
//                TextField(
//                    value = searchQuery,
//                    onValueChange = { searchQuery = it },
//                    placeholder = {
//                        Text(
//                            "Search...",
//                            color = extraColors.white.copy(alpha = 0.5f)
//                        )
//                    },
//                    leadingIcon = {
//                        Icon(
//                            imageVector = Icons.Rounded.Search,
//                            contentDescription = "Search Icon",
//                            tint = extraColors.white.copy(alpha = 0.7f)
//                        )
//                    },
//                    singleLine = true,
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(horizontal = 16.dp, vertical = 8.dp),
//                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
//                    colors = TextFieldDefaults.colors(
//                        focusedContainerColor = extraColors.cardBackground,
//                        unfocusedContainerColor = extraColors.cardBackground,
//                        disabledContainerColor = extraColors.cardBackground,
//                        cursorColor = extraColors.iconDarkBlue,
//                        focusedTextColor = extraColors.white,
//                        unfocusedTextColor = extraColors.white
//                    ),
//                    shape = RoundedCornerShape(16.dp)
//                )
//
//                LazyColumn(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(horizontal = 8.dp, vertical = 8.dp)
//                ) {
//                    items(filteredOptions) { option ->
//                        ListItem(
//                            headlineContent = {
//                                Text(
//                                    option,
//                                    color = extraColors.white
//                                )
//                            },
//                            modifier = Modifier
//                                .clickable(enabled = enabled) {
//                                    onOptionSelected(option)
//                                    searchQuery = ""
//                                    coroutineScope.launch {
//                                        sheetState.hide()
//                                        showSheet = false
//                                    }
//                                }
//                                .padding(vertical = 4.dp),
//                            trailingContent = {
//                                if (option == selectedOption) {
//                                    Icon(
//                                        imageVector = Icons.Rounded.Check,
//                                        contentDescription = "Selected",
//                                        tint = extraColors.iconDarkBlue
//                                    )
//                                }
//                            },
//                            colors = ListItemDefaults.colors(
//                                containerColor = if (option == selectedOption)
//                                    extraColors.iconDarkBlue.copy(alpha = 0.2f)
//                                else
//                                    Color.Transparent
//                            )
//                        )
//
//                        if (option != filteredOptions.last()) {
//                            HorizontalDivider(
//                                color = extraColors.white.copy(alpha = 0.1f),
//                                modifier = Modifier.padding(horizontal = 16.dp)
//                            )
//                        }
//                    }
//
//                    if (filteredOptions.isEmpty()) {
//                        item {
//                            ListItem(
//                                headlineContent = {
//                                    Text(
//                                        "No results found",
//                                        color = extraColors.white.copy(alpha = 0.5f)
//                                    )
//                                },
//                                colors = ListItemDefaults.colors(
//                                    containerColor = Color.Transparent
//                                )
//                            )
//                        }
//                    }
//                }
//            }
//        }
//    }
//}

package com.informatique.tawsekmisr.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowDropDown
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.informatique.tawsekmisr.ui.theme.LocalExtraColors
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomDropdown(
    label: String,
    options: List<String>,
    selectedOption: String?,
    onOptionSelected: (String) -> Unit,
    error: String? = null,
    mandatory: Boolean = false,
    enabled: Boolean = true,
    leadingIcon: ImageVector? = null,
    placeholder: String? = null
) {
    var showSheet by remember { mutableStateOf(false) }
    var searchQuery by remember { mutableStateOf("") }
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val coroutineScope = rememberCoroutineScope()
    val extraColors = LocalExtraColors.current

    val filteredOptions = options.filter {
        it.contains(searchQuery, ignoreCase = true)
    }

    Column(modifier = Modifier.fillMaxWidth()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(enabled = enabled) {
                    showSheet = true
                }
        ) {
            OutlinedTextField(
                value = selectedOption ?: "",
                onValueChange = {},
                shape = RoundedCornerShape(16.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = extraColors.cardBackground,
                    unfocusedContainerColor = extraColors.cardBackground,
                    disabledContainerColor = extraColors.cardBackground,
                    focusedBorderColor = if (error != null) Color(0xFFE74C3C) else Color.Transparent,
                    unfocusedBorderColor = Color.Transparent,
                    disabledBorderColor = Color.Transparent,
                    focusedTextColor = extraColors.textBlue,
                    unfocusedTextColor = extraColors.textBlue,
                    disabledTextColor = extraColors.textBlue
                ),
                readOnly = true,
                enabled = false,
                placeholder = {
                    if (selectedOption == null && placeholder != null) {
                        Text(
                            text = placeholder,
                            color = extraColors.textDarkGray,
                            fontSize = 16.sp
                        )
                    }
                },
                leadingIcon = if (leadingIcon != null) {
                    {
                        Icon(
                            imageVector = leadingIcon,
                            contentDescription = null,
                            tint = extraColors.iconDarkBlue,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                } else null,
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Rounded.ArrowDropDown,
                        contentDescription = "Dropdown Icon",
                        tint = if (enabled) extraColors.textDarkGray
                        else extraColors.textGray.copy(alpha = 0.3f)
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                isError = error != null,
                singleLine = true
            )
        }

        if (error != null) {
            Text(
                text = error,
                fontSize = 12.sp,
                color = Color(0xFFE74C3C),
                modifier = Modifier.padding(top = 4.dp, start = 4.dp)
            )
        }
    }

    if (showSheet) {
        ModalBottomSheet(
            onDismissRequest = {
                showSheet = false
                searchQuery = ""
            },
            sheetState = sheetState,
            containerColor = extraColors.background,
            shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                // Header with title and close button
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Close button
                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .clip(CircleShape)
                            .background(extraColors.cardBackground)
                            .clickable {
                                coroutineScope.launch {
                                    sheetState.hide()
                                    showSheet = false
                                    searchQuery = ""
                                }
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "تم",
                            color = extraColors.iconDarkBlue,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }

                    Spacer(modifier = Modifier.weight(1f))

                    // Title
                    Text(
                        text = if (mandatory) "$label *" else label,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = extraColors.white,
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    // Invisible spacer for balance
                    Spacer(modifier = Modifier.size(48.dp))
                }

                // Search field
                TextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Rounded.Search,
                            contentDescription = "Search Icon",
                            tint = extraColors.textDarkGray,
                            modifier = Modifier.size(22.dp)
                        )
                    },
                    placeholder = {
                        Text(
                            "ابحث عن محافظة ....",
                            color = extraColors.textDarkGray,
                            fontSize = 15.sp,
                            textAlign = TextAlign.Start,
                            modifier = Modifier.fillMaxWidth()
                        )
                    },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .padding(bottom = 12.dp),
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = extraColors.cardBackground,
                        unfocusedContainerColor = extraColors.cardBackground,
                        disabledContainerColor = extraColors.cardBackground,
                        cursorColor = extraColors.iconDarkBlue,
                        focusedTextColor = extraColors.white,
                        unfocusedTextColor = extraColors.white,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    shape = RoundedCornerShape(16.dp)
                )

                // Options list
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp)
                ) {
                    items(filteredOptions) { option ->
                        val isSelected = option == selectedOption

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(16.dp))
                                .background(
                                    if (isSelected)
                                        extraColors.iconDarkBlue.copy(alpha = 0.15f)
                                    else
                                        extraColors.cardBackground
                                )
                                .clickable(enabled = enabled) {
                                    onOptionSelected(option)
                                    searchQuery = ""
                                    coroutineScope.launch {
                                        sheetState.hide()
                                        showSheet = false
                                    }
                                }
                                .padding(horizontal = 16.dp, vertical = 16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            // Leading icon
                            if (leadingIcon != null) {
                                Box(
                                    modifier = Modifier
                                        .size(48.dp)
                                        .clip(CircleShape)
                                        .background(extraColors.iconDarkBlue.copy(alpha = 0.15f)),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        imageVector = leadingIcon,
                                        contentDescription = null,
                                        tint = extraColors.iconDarkBlue,
                                        modifier = Modifier.size(24.dp)
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.width(12.dp))
                            // Option text
                            Text(
                                text = option,
                                color = extraColors.white,
                                fontSize = 16.sp,
                                fontWeight = if (isSelected) FontWeight.Medium else FontWeight.Normal,
                                textAlign = TextAlign.End
                            )
                            Spacer(modifier = Modifier.weight(1f))
                            // Selection indicator (circle)
                            Box(
                                modifier = Modifier
                                    .size(28.dp)
                                    .clip(CircleShape)
                                    .background(
                                        if (isSelected)
                                            extraColors.iconDarkBlue
                                        else
                                            Color.Transparent
                                    )
                                    .then(
                                        if (!isSelected)
                                            Modifier.background(
                                                extraColors.white.copy(alpha = 0.2f),
                                                CircleShape
                                            )
                                        else Modifier
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                if (isSelected) {
                                    Icon(
                                        imageVector = Icons.Rounded.Check,
                                        contentDescription = "Selected",
                                        tint = Color.White,
                                        modifier = Modifier.size(18.dp)
                                    )
                                }
                            }

                        }

                        if (option != filteredOptions.last()) {
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                    }

                    if (filteredOptions.isEmpty()) {
                        item {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 32.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    "لا توجد نتائج",
                                    color = extraColors.white.copy(alpha = 0.5f),
                                    fontSize = 16.sp
                                )
                            }
                        }
                    }

                    // Bottom padding
                    item {
                        Spacer(modifier = Modifier.height(24.dp))
                    }
                }
            }
        }
    }
}