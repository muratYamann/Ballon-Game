package com.oyun;

import com.badlogic.gdx.Game;


public class Oyun extends Game {

	GameStart gameStart;
	@Override
	public void create() {
		gameStart =new GameStart();

		setScreen(gameStart);
	}

	@Override
	public void dispose() {
		super.dispose();

	}
}
