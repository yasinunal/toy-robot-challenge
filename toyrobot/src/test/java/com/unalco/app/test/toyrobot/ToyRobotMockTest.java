package com.unalco.app.test.toyrobot;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.unalco.app.toyrobot.CommandProvider;
import com.unalco.app.toyrobot.ToyRobot;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ToyRobotMockTest {

	@Mock
	CommandProvider commandProviderMock;
	
	@InjectMocks
	ToyRobot myRobot;
	
	@Test
	public void executeAllCommands() {
		
		when(commandProviderMock.generate()).thenReturn(Arrays.asList(
			"PLACE 0,0,NORTH", "MOVE", "MOVE", "RIGHT", "MOVE", "REPORT"
		));
		
		assertEquals("Output => 1,2,EAST", myRobot.executeAllCommands());
		
	}
	
}
