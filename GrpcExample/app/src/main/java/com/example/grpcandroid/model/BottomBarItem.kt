package com.example.grpcandroid.model

sealed class BottomBarItem(val route: String) {
    object BlockingTabItem : BottomBarItem(blockingTab)
    object NonBlockingTabItem : BottomBarItem(nonBlockingTab)


    object ProfileTabItem : BottomBarItem(settingsTab)
    object FinancialTabItem : BottomBarItem(transactionsTab)
    object LoanHistoryTabItem : BottomBarItem(studyTab)
}