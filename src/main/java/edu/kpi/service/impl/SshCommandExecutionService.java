package edu.kpi.service.impl;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import edu.kpi.constants.Constants;
import edu.kpi.parameters.SshCommandParameter;
import edu.kpi.service.ExecutionService;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Optional;
import java.util.function.Predicate;

public class SshCommandExecutionService implements ExecutionService<SshCommandParameter> {

    @Override
    public String execute(final SshCommandParameter parameter) {

        Session session = null;
        ChannelExec channel = null;

        try {

            session = getSession(parameter);
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

    protected Session createSession(final SshCommandParameter parameter) throws Exception {

        return new JSch().getSession(parameter.getUsername(), parameter.getHost(), parameter.getPort());
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

    private Session getSession(final SshCommandParameter parameter) throws Exception {

        final Session session = createSession(parameter);
        session.setPassword(parameter.getPassword());
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
