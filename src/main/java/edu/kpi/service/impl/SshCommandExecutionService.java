package edu.kpi.service.impl;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import edu.kpi.constants.Constants;
import edu.kpi.dto.ConnectionDto;
import edu.kpi.parameters.SshCommandParameter;
import edu.kpi.service.ExecutionServiceLocal;
import jakarta.ejb.Stateless;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Optional;
import java.util.function.Predicate;

@Stateless
public class SshCommandExecutionService implements ExecutionServiceLocal<SshCommandParameter> {

    @Override
    public String execute(final SshCommandParameter parameter) {

        Session session = null;
        ChannelExec channel = null;

        try {

            session = getSession(parameter.getConnection());
            session.connect();

            OutputStream responseStream = createOutputStream();

            channel = createChannel(parameter, session, responseStream);
            channel.connect();

            waitForExecution(channel);

            return Optional.of(responseStream.toString())
                    .filter(Predicate.not(Constants.Format.EMPTY_STRING::equals))
                    .orElseGet(this::createGenericResponse);

        } catch (final Exception e) {

            return String.format(Constants.Messages.ERROR_RECEIVED, e.getMessage());

        } finally {

            Optional.ofNullable(session).ifPresent(Session::disconnect);
            Optional.ofNullable(channel).ifPresent(Channel::disconnect);
        }
    }

    protected Session createSession(final ConnectionDto connection) throws Exception {

        return new JSch().getSession(connection.getUsername(), connection.getHost(), connection.getPort());
    }

    protected OutputStream createOutputStream() {

        return new ByteArrayOutputStream();
    }

    protected String createGenericResponse() {

        return String.format(Constants.Messages.GENERIC_EXECUTION_COMPLETED_MESSAGE, DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL).format(ZonedDateTime.now()));
    }

    protected int getCheckInterval() {

        return Constants.Configuration.CHECK_INTERVAL;
    }

    protected int getTimeout() {

        return Constants.Configuration.TIMEOUT;
    }

    private Session getSession(final ConnectionDto connection) throws Exception {

        final Session session = createSession(connection);
        session.setPassword(connection.getPassword());
        session.setConfig(Constants.Configuration.STRICT_HOST_KEY_CHECKING, Constants.Configuration.DISABLED);

        return session;
    }

    private ChannelExec createChannel(final SshCommandParameter parameter, final Session session, final OutputStream outputStream) throws Exception {

        final ChannelExec channel = (ChannelExec) session.openChannel(Constants.Configuration.CHANNEL);
        channel.setCommand(parameter.getCommand());
        channel.setOutputStream(outputStream);

        return channel;
    }

    private void waitForExecution(final Channel channel) throws InterruptedException {

        for (int i = 0; channel.isConnected(); i++) {

            if (getCheckInterval() * i > getTimeout()) {

                throw new IllegalStateException(String.format(Constants.Messages.TIMEOUT_MESSAGE, getTimeout()));
            }

            Thread.sleep(getCheckInterval());
        }
    }
}
