package com.example.grpcandroid.model

sealed class MainScreenTabRoute(val name: String) {
    object BlockingTab: MainScreenTabRoute(blockingTab)
    object NonBlockingTab: MainScreenTabRoute(nonBlockingTab)
    object SettingsTab: MainScreenTabRoute(settingsTab)
    object FinancialTab: MainScreenTabRoute(transactionsTab)
    object StudyTab: MainScreenTabRoute(studyTab)
}
