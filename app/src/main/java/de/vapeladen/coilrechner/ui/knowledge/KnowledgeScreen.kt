package de.vapeladen.coilrechner.ui.knowledge

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import de.vapeladen.coilrechner.R
import de.vapeladen.coilrechner.data.model.KnowledgeDataManager

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KnowledgeScreen() {
    var selectedTabIndex by remember { mutableStateOf(0) }
    val (tab1, tab2, tab3) = KnowledgeDataManager.getTabNames()
    val tabs = listOf(tab1, tab2, tab3)
    
    Column(modifier = Modifier.fillMaxSize()) {
        // Tab Row
        TabRow(
            selectedTabIndex = selectedTabIndex,
            containerColor = Color(0xFF6639B7),
            contentColor = Color.White
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = { selectedTabIndex = index },
                    text = { 
                        Text(
                            text = title,
                            fontWeight = if (selectedTabIndex == index) FontWeight.Bold else FontWeight.Normal
                        )
                    }
                )
            }
        }
        
        // Tab Content
        when (selectedTabIndex) {
            0 -> DictionaryTab()
            1 -> FAQTab()
            2 -> WiresTab()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DictionaryTab() {
    val entries = KnowledgeDataManager.getDictionaryEntries()
    
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        contentPadding = PaddingValues(vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(entries) { entry ->
            var expanded by remember { mutableStateOf(false) }
            
            Card(
                onClick = { expanded = !expanded },
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = if (expanded) Color(0xFF6639B7) else Color(0xFF424250)
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        text = entry.title,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    
                    if (expanded) {
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = entry.content,
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FAQTab() {
    val entries = KnowledgeDataManager.getFAQEntries()
    
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        contentPadding = PaddingValues(vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(entries) { entry ->
            var expanded by remember { mutableStateOf(false) }
            
            Card(
                onClick = { expanded = !expanded },
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = if (expanded) Color(0xFF6639B7) else Color(0xFF424250)
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        text = entry.question,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    
                    if (expanded) {
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = entry.answer,
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WiresTab() {
    val entries = KnowledgeDataManager.getWireEntries()
    
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        contentPadding = PaddingValues(vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(entries) { entry ->
            var expanded by remember { mutableStateOf(false) }
            
            Card(
                onClick = { expanded = !expanded },
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = if (expanded) Color(0xFF6639B7) else Color(0xFF424250)
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        text = entry.name,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    
                    if (expanded) {
                        Spacer(modifier = Modifier.height(12.dp))
                        
                        // Composition
                        Text(
                            text = stringResource(R.string.knowledge_composition),
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        Text(
                            text = entry.composition,
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.White,
                            modifier = Modifier.padding(vertical = 4.dp)
                        )
                        
                        Spacer(modifier = Modifier.height(8.dp))
                        
                        // Properties
                        Text(
                            text = stringResource(R.string.knowledge_properties),
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        Text(
                            text = entry.properties,
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.White,
                            modifier = Modifier.padding(vertical = 4.dp)
                        )
                        
                        Spacer(modifier = Modifier.height(8.dp))
                        
                        // Pros
                        Text(
                            text = stringResource(R.string.knowledge_pros),
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF4CAF50)
                        )
                        entry.pros.forEach { pro ->
                            Text(
                                text = "• $pro",
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color.White,
                                modifier = Modifier.padding(start = 8.dp, top = 2.dp)
                            )
                        }
                        
                        Spacer(modifier = Modifier.height(8.dp))
                        
                        // Cons
                        Text(
                            text = stringResource(R.string.knowledge_cons),
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFF44336)
                        )
                        entry.cons.forEach { con ->
                            Text(
                                text = "• $con",
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color.White,
                                modifier = Modifier.padding(start = 8.dp, top = 2.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}