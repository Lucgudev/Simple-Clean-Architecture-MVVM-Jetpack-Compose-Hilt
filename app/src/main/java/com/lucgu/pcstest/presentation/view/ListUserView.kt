package com.lucgu.pcstest.presentation.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.lucgu.pcstest.domain.entities.UserEntity
import com.lucgu.pcstest.helper.DateTimeUtil
import coil.compose.AsyncImage
import com.lucgu.pcstest.R

@Composable
fun ListUserView(data: List<UserEntity>, onClickItem: (UserEntity) -> Unit) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(1),
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(start = 16.dp, end = 16.dp),
        horizontalArrangement = Arrangement.Center,
    ) {
        items(data) { data ->
            Card(
                colors = CardDefaults.cardColors(Color.White),
                modifier = Modifier.padding(start = 4.dp, end = 4.dp, top = 4.dp, bottom = 4.dp).clickable {
                    onClickItem(data)
                },
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 10.dp
                ),
            ) {
                Row(
                    modifier = Modifier.padding(16.dp)
                ) {
                    AsyncImage(
                        model = data.avatar,
                        contentDescription = null,
                        modifier = Modifier
                            .size(width = 80.dp, height = 60.dp)
                            .clip(RoundedCornerShape(8.dp)),
                        contentScale = ContentScale.Crop
                    )
                    Column (
                        modifier = Modifier.padding(start = 8.dp)
                    ){
                        Text(text = data.name, style = MaterialTheme.typography.bodyMedium)
                        Text(
                            text = stringResource(
                                R.string.data_entry,
                                DateTimeUtil.formatTimestamp(data.createdAt)
                            ),
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
            }
        }
    }
}