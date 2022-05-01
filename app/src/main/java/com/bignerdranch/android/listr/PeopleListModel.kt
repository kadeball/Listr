package com.bignerdranch.android.listr

import androidx.lifecycle.ViewModel

class PeopleListModel: ViewModel() {

    val people = listOf(
        Person("Katy", "Perry"),
        Person("Taylor", "Swift"),
        Person("John", "Mayer"),
        Person("Ed", "Sheeran"),
        Person("Lana", "Del Rey"),
        Person("Britney", "Spears"),
        Person("Rina", "Sawayama"),
        Person("Ariana", "Grande"),
        Person("Jessie", "J"),
        Person("Carlie", "Hanson"),
        Person("Astrid", "S"),
        Person("Charlotte", "Lawrence"),
        Person("James", "Blake"),
        Person("Christina", "Aguilera"),
        Person("Avril", "Lavigne"),
        Person("Doja", "Cat"),
        Person("Paloma", "Faith")
    )

}