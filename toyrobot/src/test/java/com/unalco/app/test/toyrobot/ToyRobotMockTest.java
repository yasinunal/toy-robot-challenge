package com.unalco.app.test.toyrobot;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.unalco.app.toyrobot.data.CommandProvider;
import com.unalco.app.toyrobot.model.ToyRobot;
import com.unalco.app.toyrobot.service.ToyRobotEngine;

@RunWith(MockitoJUnitRunner.class)
public class ToyRobotMockTest {

	@Mock
	CommandProvider commandProviderMock;

	@InjectMocks
	ToyRobotEngine myRobotEngine;
		
	@Test
	public void executeScenario1() {
		
		when(commandProviderMock.generate()).thenReturn(Arrays.asList(
			"PLACE 0,0,EAST", "MOVE", "MOVE", "RIGHT", "MOVE", "LEFT", "MOVE", "REPORT"
		));

		ToyRobot myRobot = new ToyRobot();
				
		myRobotEngine = new ToyRobotEngine(commandProviderMock, myRobot);
		
		myRobotEngine.executeAllCommands();
		
		assertEquals("3, 0, EAST", myRobotEngine.getMyRobot().report());
		
		
	}
	
}
