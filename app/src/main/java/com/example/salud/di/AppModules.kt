package com.example.salud.di

import com.google.firebase.ktx.Firebase
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AuthModule::class, FirebaseModule::class,VitalsModule::class])
@Singleton
interface AppModules