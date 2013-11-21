package com.me.android_game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class GameMain implements ApplicationListener {
	private MyController myController;
	private ShapeRenderer apple;
	private Team[][] teams; 
	private float scale;
	private float screenHeight;
	private float screenWidth;
	
	@Override
	public void create() {
		
		
		screenHeight = Gdx.graphics.getHeight();
		screenWidth = Gdx.graphics.getWidth();
		
		if(screenHeight >= screenWidth){
			scale = (screenWidth/8);
		}
		else{
			scale = (screenHeight / 8);
		}
		
		
		
		apple = new ShapeRenderer();
		myController = new MyController(8, scale);
		teams = myController.getPiecesTeams();
		
		Gdx.input.setInputProcessor(myController);
		
		
	}

	@Override
	public void dispose() {
	}

	@Override
	public void render() {
		
	
		
		teams = myController.getPiecesTeams();
		
		
		
		apple.begin(ShapeType.Filled);
		
		for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                // sets checker board colors
                if (row % 2 == col % 2) {
                	apple.setColor(Color.LIGHT_GRAY);
                } else {
                	apple.setColor(Color.GRAY);
                }
                apple.rect((col* scale), (row * scale), scale, scale);
                
                
                

                // sets pieces on the board
                if (teams[row][col] == Team.TEAM1) {
                	apple.setColor(Color.RED);
                    apple.circle(0+(int)((col+0.5) * scale), 0+(int)((row+0.5) * scale), scale/4);
                } else if (teams[row][col] == Team.TEAM2) {
                	apple.setColor(Color.BLUE);
                    apple.circle(0+(int)((col+0.5) * scale), 0+(int)((row+0.5) * scale), scale/4);
                } else {
                    // neutral
                	apple.setColor(Color.WHITE);
                    apple.circle(0+(int)((col+0.5) * scale), 0+(int)((row+0.5) * scale), scale/4);
                }
                
                
            }
        }
		
		apple.end();

	}

	@Override
	public void resize(int width, int height) {
		

	}

	@Override
	public void pause() {
	}
	
	// only on android
	@Override
	public void resume() {
	}
}
