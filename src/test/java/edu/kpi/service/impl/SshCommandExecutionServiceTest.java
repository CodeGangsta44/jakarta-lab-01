package edu.kpi.service.impl;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import edu.kpi.dto.ConnectionDto;
import edu.kpi.parameters.SshCommandParameter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.ByteArrayOutputStream;

import static edu.kpi.constants.Constants.Configuration.*;
import static edu.kpi.constants.Constants.Format.EMPTY_STRING;

@RunWith(MockitoJUnitRunner.class)
public class SshCommandExecutionServiceTest {

    private static final String USERNAME_PARAMETER = "username";
    private static final String HOST_PARAMETER = "host";
    private static final int PORT_PARAMETER = 22;
    private static final String PASSWORD_PARAMETER = "password";
    private static final String COMMAND_PARAMETER = "command";
    private static final String EXECUTION_RESULT = "executionResult";
    private static final String GENERIC_RESULT = "genericResult";
    private static final int TIMEOUT = 10;
    private static final int CHECK_INTERVAL = 1;
    private static final String EXPECTED_TIMEOUT_MESSAGE = "Error received: Execution failed due to timeout: 10ms";

    @Mock
    private Session session;
    @Mock
    private ChannelExec channel;
    @Mock
    private ByteArrayOutputStream outputStream;

    @Spy
    private SshCommandExecutionService unit;

    private SshCommandParameter parameter;

    @Before
    public void setUp() throws Exception {

        final ConnectionDto connection = ConnectionDto.builder()
                .username(USERNAME_PARAMETER)
                .host(HOST_PARAMETER)
                .port(PORT_PARAMETER)
                .password(PASSWORD_PARAMETER)
                .build();

        parameter = SshCommandParameter.builder()
                .connection(connection)
                .command(COMMAND_PARAMETER)
                .build();

        Mockito.doReturn(session).when(unit).createSession(connection);
        Mockito.doReturn(outputStream).when(unit).createOutputStream();
        Mockito.doReturn(GENERIC_RESULT).when(unit).createGenericResponse();
        Mockito.doReturn(CHECK_INTERVAL).when(unit).getCheckInterval();
        Mockito.doReturn(TIMEOUT).when(unit).getTimeout();

        Mockito.when(session.openChannel(CHANNEL)).thenReturn(channel);
        Mockito.when(outputStream.toString()).thenReturn(EXECUTION_RESULT);
        Mockito.when(channel.isConnected()).thenReturn(Boolean.FALSE);
    }

    @Test
    public void shouldExecuteCommand() throws JSchException {

        final String actual = unit.execute(parameter);

        Mockito.verify(session).setPassword(PASSWORD_PARAMETER);
        Mockito.verify(session).setConfig(STRICT_HOST_KEY_CHECKING, DISABLED);
        Mockito.verify(session).connect();
        Mockito.verify(session).disconnect();

        Mockito.verify(channel).setCommand(COMMAND_PARAMETER);
        Mockito.verify(channel).setOutputStream(outputStream);
        Mockito.verify(channel).connect();
        Mockito.verify(channel).disconnect();

        Assert.assertEquals(EXECUTION_RESULT, actual);
    }

    @Test
    public void shouldReturnGenericResultWhenOutputStreamIsEmpty() throws JSchException {

        Mockito.when(outputStream.toString()).thenReturn(EMPTY_STRING);

        Assert.assertEquals(GENERIC_RESULT, unit.execute(parameter));
    }

    @Test
    public void shouldReturnTimeoutMessageWhenChannelIsStuck() throws JSchException {

        Mockito.when(channel.isConnected()).thenReturn(Boolean.TRUE);

        Assert.assertEquals(EXPECTED_TIMEOUT_MESSAGE, unit.execute(parameter));
    }
}