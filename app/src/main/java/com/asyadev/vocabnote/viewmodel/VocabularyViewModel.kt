package com.asyadev.vocabnote.viewmodel

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.asyadev.vocabnote.database.Vocabulary
import com.asyadev.vocabnote.database.VocabularyRepository
import kotlinx.coroutines.launch

class VocabularyViewModel(private val repository: VocabularyRepository): ViewModel() {
    var vocabularyList: LiveData<List<Vocabulary>> = repository.allVocabulary.asLiveData()
    fun addVocabulary(newVocabulary: Vocabulary) = viewModelScope.launch {
        repository.insert(newVocabulary)
    }

    fun updateVocabulary(vocabulary: Vocabulary) = viewModelScope.launch {
        repository.update(vocabulary)
    }

    fun deleteVocabulary(vocabulary: Vocabulary) = viewModelScope.launch {
        repository.delete(vocabulary)
    }
}

class VocabularyViewModelFactory(private val repository: VocabularyRepository): ViewModelProvider.Factory {
    override fun <T: ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(VocabularyViewModel::class.java)){
            return VocabularyViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown Class for View Model")
    }
}