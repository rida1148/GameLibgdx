package com.me.android_game;

import com.badlogic.gdx.InputProcessor;


public class MyController implements InputProcessor{
	

	    private final Team[][] pieces;
	    private final float SCALE;
	    private Team team;
	    private final int numSquares;
	    private boolean win;
	    

    public MyController(int numSquares, float scale) {
    	
    	SCALE = scale;
    	this.team = Team.TEAM1;
    	this.numSquares = numSquares = 8;

        pieces = new Team[numSquares][numSquares];

        // sets up the pieces on the board.
        for (int r = 0; r < numSquares; r++) {
            for (int c = 0; c < numSquares; c++) {
                // makes square (3,3) team 1
                if (r == 3 && c == 3) {
                    pieces[r][c] = Team.TEAM1;
                } // makes square (3,4) team 1
                else if (r == 3 && c == 4) {
                    pieces[r][c] = Team.TEAM2;
                } // makes square (4,3) team 1
                else if (r == 4 && c == 3) {
                    pieces[r][c] = Team.TEAM2;
                } // makes square (4,4) team 1
                else if (r == 4 && c == 4) {
                    pieces[r][c] = Team.TEAM1;
                } // if it's not a center square make it neutral
                else {
                    pieces[r][c] = Team.NEUTRAL;
                }

            }
        }
    }

    

    public Team[][] getPiecesTeams() {
        return pieces;
    }



	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		
		
		// here
		
        float y = screenX;
        float x = screenY;
        
        System.out.println("x: " + x+ " "+ "y: "+ y);
        
        float squareSize = SCALE;
 

        if (x > 2 && x < numSquares * squareSize + 2
                && y > 2 && y < numSquares * squareSize + 2) {
            float row = x / squareSize;
            float column = y / squareSize;
            reverse(row, column);
        } else {
            System.out.println("You clicked outside the board!");
        }
		
		return false;
	}



	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	
	// reverse
	
	private void reverse(float row1, float column2) {
		
		int row = (int) row1;
		int column = (int) column2;
		
		System.out.println("row: " + row + " column: " + column);
		

        // If a piece is not neutral, don't continue.
        if (pieces[row][column] != Team.NEUTRAL) {
            return;
        }

        boolean set = false;

        // Vertical Up
        for (int r = row - 1; r >= 0; r--) {
            if (pieces[r][column] == Team.NEUTRAL) {
                break;
            } else if (pieces[r][column] == team) {
                if (row - r > 1) {
                    for (r++; r <= row; r++) {
                        pieces[r][column] = team;
                    }
                    set = true;
                }
                break;
            }
        }

        // Vertical Down
        for (int r = row + 1; r < numSquares; r++) {
            if (pieces[r][column] == Team.NEUTRAL) {
                break;
            } else if (pieces[r][column] == team) {
                if (r - row > 1) {
                    for (r--; r > row; r--) {
                        pieces[r][column] = team;
                    }
                    set = true;
                }
                break;
            }
        }

        // Horizontal Right
        for (int c = column + 1; c < numSquares; c++) {
            if (pieces[row][c] == Team.NEUTRAL) {
                break;
            } else if (pieces[row][c] == team) {
                if (c - column > 1) {
                    for (c--; c > column; c--) {
                        pieces[row][c] = team;
                    }
                    set = true;
                }
                break;
            }
        }

        // Horizontal Left
        for (int c = column - 1; c >= 0; c--) {
            if (pieces[row][c] == Team.NEUTRAL) {
                break;
            } else if (pieces[row][c] == team) {
                if (column - c > 1) {
                    for (c++; c < column; c++) {
                        pieces[row][c] = team;
                    }
                    set = true;
                }
                break;
            }
        }

        // Digonal cell to top-left
        for (int c = column - 1, r = row - 1; c >= 0 && r >= 0; c--, r--) {
            if (pieces[r][c] == Team.NEUTRAL) {
                break;
            } else if (pieces[r][c] == team) {
                if (column - c > 1 && row - r > 1) {
                    for (c++, r++; c < column && r < row; c++, r++) {
                        pieces[r][c] = team;
                    }
                    set = true;
                }
                break;
            }
        }

        // Digonal cell to bottom-right
        for (int c = column + 1, r = row + 1; c < numSquares && r < numSquares; c++, r++) {
            if (pieces[r][c] == Team.NEUTRAL) {
                break;
            } else if (pieces[r][c] == team) {
                if (c - column > 1 && r - row > 1) {
                    for (c--, r--; c > column && r > row; c--, r--) {
                        pieces[r][c] = team;
                    }
                    set = true;
                }
                break;
            }
        }

        // Digonal cell to bottom-left
        for (int c = column - 1, r = row + 1; c >= 0 && r < numSquares; c--, r++) {
            if (pieces[r][c] == Team.NEUTRAL) {
                break;
            } else if (pieces[r][c] == team) {
                if (column - c > 1 && r - row > 1) {
                    for (c++, r--; c < column && r > row; c++, r--) {
                        pieces[r][c] = team;
                    }
                    set = true;
                }
                break;
            }
        }

        // Digonal cell to top-right
        for (int c = column + 1, r = row - 1; c < numSquares && r >= 0; c++, r--) {
            if (pieces[r][c] == Team.NEUTRAL) {
                break;
            } else if (pieces[r][c] == team) {
                if (c - column > 1 && row - r > 1) {
                    for (c--, r++; c > column && r < row; c--, r++) {
                        pieces[r][c] = team;
                    }
                    set = true;
                }
                break;
            }
        }

        if (set) {
            pieces[row][column] = team;
            team = team == Team.TEAM1 ? Team.TEAM2 : Team.TEAM1;
            Team winner = checkForWin();
            if (winner == Team.TEAM1){
                // win
            } else if (winner == Team.TEAM2){
                // win
            } else if (winner == Team.NEUTRAL){
                // win
            }
        }
    }
	
	public Team checkForWin(){
        int numTeam1 = 0;
        int numTeam2 = 0;
        boolean full = true;
        for (Team[] row : pieces){
            for (Team cell : row){
                if (cell == Team.TEAM1){
                    numTeam1++;
                } else if (cell == Team.TEAM2){
                    numTeam2++;
                } else {
                    full = false;
                }
            }
        }
        if (full){
            win = true;
            if (numTeam1 > numTeam2){
                return Team.TEAM1; //Full board, team1 has more
            } else if (numTeam2 > numTeam1){
                return Team.TEAM2; //Full board, team2 has more
            } else {
                return Team.NEUTRAL; //Tie
            }
        } else {
            if (numTeam1 == 0){
                win = true;
                return Team.TEAM2; //team1 has no more pieces
            } else if (numTeam2 == 0){
                win = true;
                return Team.TEAM1; //team2 has no more pieces
            } else {
                return Team.UNKNOWN; //No one wins
            }
        }
    }
    
}
