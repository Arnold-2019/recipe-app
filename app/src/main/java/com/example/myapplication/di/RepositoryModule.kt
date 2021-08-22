package com.example.myapplication.di

import com.example.myapplication.network.RecipeDtoMapper
import com.example.myapplication.network.RecipeService
import com.example.myapplication.repository.RecipeRepository
import com.example.myapplication.repository.RecipeRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideRecipeRepository(
        recipeService: RecipeService,
        recipeMapper: RecipeDtoMapper
    ): RecipeRepository {
        return RecipeRepositoryImpl(recipeService = recipeService, mapper = recipeMapper)
    }
}
