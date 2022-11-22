package edu.kpi.constants;

public class Constants {

    public static class Pages {

        public static final String ROOT = "/index.jsp";
        public static final String SSH_COMMAND = "/command/sshCommand.jsp";
        public static final String REGISTER = "/auth/register.jsp";
        public static final String HOME = "/home/home.jsp";
        public static final String LOGIN = "/auth/login.jsp";
        public static final String CONNECTION_FORM = "/connection/connectionForm.jsp";
        public static final String HISTORY = "/history/history.jsp";
    }

    public static class Parameters {

        public static final String USERNAME = "username";
        public static final String HOST = "host";
        public static final String PORT = "port";
        public static final String PASSWORD = "password";
        public static final String COMMAND = "command";
        public static final String RESULT = "result";
        public static final String REPEATED_PASSWORD = "repeatedPassword";
        public static final String STATUS = "status";
        public static final String CONNECTION_NAME = "connectionName";
        public static final String ID = "id";
        public static final String FAILED_ATTEMPT = "failedAttempt";
        public static final String AFTER_REGISTRATION = "afterRegistration";
        public static final String CONNECTIONS_IN_ROW = "connectionsInRow";
        public static final String METHOD = "method";
        public static final String CONNECTION = "connection";
        public static final String ENTRIES = "entries";
        public static final String HISTORY_ENTRY_ID = "historyEntryId";
    }

    public static class Format {

        public static final String LINE_ENDING = "\n";
        public static final String BREAK = "<br/>";
        public static final String EMPTY_STRING = "";
    }

    public static class Configuration {

        public static final String CHANNEL = "exec";
        public static final int TIMEOUT = 10000;
        public static final int CHECK_INTERVAL = 100;
        public static final String STRICT_HOST_KEY_CHECKING = "StrictHostKeyChecking";
        public static final String DISABLED = "no";
        public static final int CONNECTIONS_IN_ROW = 3;
    }

    public static class Messages {

        public static final String ERROR_RECEIVED = "Error received: %s";
        public static final String TIMEOUT_MESSAGE = "Execution failed due to timeout: %sms";
        public static final String GENERIC_EXECUTION_COMPLETED_MESSAGE = "[EXECUTION COMPLETED] - %s";
    }

    public static class Headers {

        public static final String REFERER = "Referer";
    }

    public static class Methods {

        public static final String GET = "get";
        public static final String POST = "post";
        public static final String PUT = "put";
        public static final String DELETE = "delete";
    }
}
