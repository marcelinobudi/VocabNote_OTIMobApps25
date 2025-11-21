package com.asyadev.vocabnote.viewmodel

import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.asyadev.vocabnote.database.Vocabulary
import com.asyadev.vocabnote.database.VocabularyRepository
import kotlinx.coroutines.launch

class VocabularyViewModel(private val repository: VocabularyRepository): ViewModel() {
    private var _allVocabulary: MutableLiveData<List<Vocabulary>> = MutableLiveData<List<Vocabulary>>()
    private var _preUpdatedVocabulary : MutableLiveData<Vocabulary> = MutableLiveData<Vocabulary>()
    val vocabularyList: LiveData<List<Vocabulary>> = _allVocabulary
    val preUpdatedVocabulary : LiveData<Vocabulary> = _preUpdatedVocabulary

    fun getVocabularyList() {
        viewModelScope.launch {
            try {
                _allVocabulary.value = repository.getVocabulary()
            } catch (e: Exception){
                Log.d("Get Vocabulary", e.message.toString())
            }

        }
    }
    fun resetVocabularyList(){
        viewModelScope.launch {
            _allVocabulary = MutableLiveData<List<Vocabulary>>()
        }
    }
    fun addVocabulary(word: String, translation: String, description: String,example: String,pronounciation: String, difficulty: String){
        if(
            word.isEmpty() ||
            translation.isEmpty() ||
            difficulty.isEmpty()
        ){
            // BISA BERI PESAN ERROR
            return
        }
        val newVocabulary = Vocabulary(
            word = word,
            translation = translation,
            description = description,
            example = example,
            pronounciation = pronounciation,
            difficulty = difficulty
        )
        viewModelScope.launch {
            try{
                repository.insert(newVocabulary)
                Log.d("Menambah vocabulary", "sukses")
            } catch(E: Exception) {
                Log.d("Menambah vocabulary", "gagal")
            }


        }
    }

    fun preUpdateVocabulary(vocabulary: Vocabulary) {
        viewModelScope.launch {
            _preUpdatedVocabulary.value = vocabulary
        }
    }
    fun updateVocabulary(vocabulary: Vocabulary) {
        viewModelScope.launch {
            repository.update(vocabulary)
        }
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