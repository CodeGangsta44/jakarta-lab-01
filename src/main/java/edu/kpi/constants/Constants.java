package edu.kpi.constants;

public class Constants {

    public static class Pages {

        public static final String SSH_COMMAND = "/sshCommand.jsp";
        public static final String REGISTER_COMMAND = "/auth/register.jsp";
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
    }

    public static class Messages {

        public static final String ERROR_RECEIVED = "Error received: %s";
        public static final String TIMEOUT_MESSAGE = "Execution failed due to timeout: %sms";
        public static final String GENERIC_EXECUTION_COMPLETED_MESSAGE = "[EXECUTION COMPLETED] - %s";
    }
}
