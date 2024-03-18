import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import id.ac.unpas.agenda.models.Register
import id.ac.unpas.agenda.persistences.RegisterDao
import kotlinx.coroutines.launch

@Composable
fun ListRegisterScreen(registerDao: RegisterDao, onEditItem: (Register?) -> Unit) {
    val _list: LiveData<List<Register>> = registerDao.loadAll()
    val list: List<Register> by _list.observeAsState(listOf())
    val scope = rememberCoroutineScope()

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(list) { item ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Column(
                    modifier = Modifier.padding(8.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        val index = list.indexOf(item) + 1
                        Text(
                            text = "Data $index",
                            style = MaterialTheme.typography.headlineLarge,
                            color = Color.Black
                        )
                        Row {
                            IconButton(onClick = {
                                onEditItem(item)
                            }) {
                                Icon(
                                    imageVector = Icons.Filled.Edit,
                                    contentDescription = "Edit",
                                    tint = Color.Black
                                )
                            }
                            IconButton(onClick = {
                                scope.launch {
                                    registerDao.delete(item.id)
                                    onEditItem(null)
                                }
                            }) {
                                Icon(
                                    imageVector = Icons.Filled.Delete,
                                    contentDescription = "Delete",
                                    tint = Color.Black
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "NISN: ${item.NISN}",
                        color = Color.Black
                    )
                    Text(
                        text = "Nama: ${item.Nama}",
                        color = Color.Black
                    )
                    Text(
                        text = "Email: ${item.Email}",
                        color = Color.Black
                    )
                    Text(
                        text = "Alamat: ${item.Alamat}",
                        color = Color.Black
                    )
                }
            }
        }
    }
}