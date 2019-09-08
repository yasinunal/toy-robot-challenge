package com.unalco.app.test.toyrobot;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.unalco.app.data.CommandProvider;
import com.unalco.app.model.Facing;
import com.unalco.app.model.Position;
import com.unalco.app.model.ToyRobot;
import com.unalco.app.toyrobot.service.ToyRobotEngine;

@RunWith(MockitoJUnitRunner.class)
public class ToyRobotMockTest {

	@Mock
	CommandProvider commandProviderMock;
	
	
	@InjectMocks
	ToyRobotEngine myRobotEngine;
	
	
	@Test
	public void executeAllCommands() {
		
		when(commandProviderMock.generate()).thenReturn(Arrays.asList(
			"PLACE 0,0,NORTH", "MOVE", "MOVE", "RIGHT", "MOVE", "REPORT"
		));

		ToyRobot myRobot = new ToyRobot(new Position(0,0,Facing.NORTH));
				
		myRobotEngine = new ToyRobotEngine(commandProviderMock, myRobot);
		
		myRobotEngine.executeAllCommands();
		
		assertEquals("1, 2, EAST", myRobotEngine.getMyRobot().report());
		
		
	}
	
}
