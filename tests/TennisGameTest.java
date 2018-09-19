import static org.junit.Assert.*;

import org.junit.Test;

import jdk.nashorn.internal.ir.annotations.Ignore;

public class TennisGameTest {
	
// Here is the format of the scores: "player1Score - player2Score"
// "love - love"
// "15 - 15"
// "30 - 30"
// "deuce"
// "15 - love", "love - 15"
// "30 - love", "love - 30"
// "40 - love", "love - 40"
// "30 - 15", "15 - 30"
// "40 - 15", "15 - 40"
// "player1 has advantage"
// "player2 has advantage"
// "player1 wins"
// "player2 wins"
	@Ignore
	public void testTennisGame_Start() {
		//Arrange
		TennisGame game = new TennisGame();
		//Act
		String score = game.getScore() ;
		// Assert
		assertEquals("Initial score incorrect", "love - love", score);		
	}
	
	@Test
	public void testTennisGame_EahcPlayerWin4Points_Score_Deuce() throws TennisGameException {
		//Arrange
		TennisGame game = new TennisGame();
		
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		
		game.player2Scored();
		game.player2Scored();
		game.player2Scored();
		
		game.player1Scored();
		game.player2Scored();
		//Act
		String score = game.getScore() ;
		// Assert
		assertEquals("Tie score incorrect", "deuce", score);		
	}
	
	@Test (expected = TennisGameException.class)
	public void testTennisGame_Player1WinsPointAfterGameEnded_ResultsException() throws TennisGameException {
		//Arrange
		TennisGame game = new TennisGame();
		//Act
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		//Act
		// This statement should cause an exception
		game.player1Scored();			
	}
	@Test (expected = TennisGameException.class)
	public void testTennisGame_Player2WinsPointAfterGameEnded_ResultsException() throws TennisGameException {
		//Arrange
		TennisGame game = new TennisGame();
		//Act
		game.player2Scored();
		game.player2Scored();
		game.player2Scored();
		game.player2Scored();
		//Act
		// This statement should cause an exception
		game.player2Scored();			
	}
	
	@Test
	public void testTennisGame_GetScoreInTennisNames() throws TennisGameException
	{
		TennisGame game = new TennisGame();
		//
		game.player1Scored();
		game.player2Scored();
		game.player1Scored();
		String score = game.getScore();
		//
		assertEquals("The game doest not show Tennish Names of score", "thirty - fifteen", score);
	}

	@Test
	public void testTennisGame_CheckPlayer1_Wins() throws TennisGameException
	{
		TennisGame game = new TennisGame();
		//
		game.player1Scored();
		game.player2Scored();
		game.player1Scored();
		game.player1Scored();
		game.player2Scored();

		game.player1Scored();
		String score = game.getScore();
		//
		assertEquals("The game doest not show player1 wins correctly", "player1 wins", score);
	}
	@Test
	public void testTennisGame_CheckPlayer2_Wins() throws TennisGameException
	{
		TennisGame game = new TennisGame();
		//
		game.player2Scored();
		game.player1Scored();
		game.player2Scored();
		game.player1Scored();
		game.player2Scored();

		game.player2Scored();
		String score = game.getScore();
		//
		assertEquals("The game doest not show player1 wins correctly", "player2 wins", score);
	}
	@Test
	public void testTennisGame_player1Score_Gets_Score() throws TennisGameException{
		TennisGame game = new TennisGame();
		//
		game.player1Scored();
		String score = game.getScore();
		assertEquals("The game doest not show Tennish Names of score", "fifteen - love", score);
	}

	@Test
	public void testTennisGame_BothSidesOver3Pts_Get_Player1_Advantage()throws TennisGameException {
		TennisGame game = new TennisGame();
		//
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();

		game.player2Scored();
		game.player2Scored();
		game.player2Scored();

		game.player1Scored();

	

		String score = game.getScore();
		assertEquals("The game doesnt show player1 advantage correctly", "player1 has advantage",score);
	}
	@Test
	public void testTennisGame_BothSidesOver3Pts_Get_Player2_Advantage()throws TennisGameException {
		TennisGame game = new TennisGame();
		//
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();

		game.player2Scored();
		game.player2Scored();
		game.player2Scored();

		game.player2Scored();

	

		String score = game.getScore();
		assertEquals("The game doesnt show player2 advantage correctly", "player2 has advantage",score);
	}
	@Test
	public void testTennisGame_BothSidesAtLeast3Pts_Get_Deuce()throws TennisGameException {
		TennisGame game = new TennisGame();
		//
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();

		game.player2Scored();
		game.player2Scored();
		game.player2Scored();	

		String score = game.getScore();
		assertEquals("The game score is not deuce", "player2 has advantage",score);
	}

}
