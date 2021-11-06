package com.yensontam.comic.mvi

interface IState {
  fun consumeAction(action: IAction) : IState
}