package fr.bred.batchtotem.exceptions;

import org.springframework.boot.ExitCodeGenerator;

public class CustomException extends RuntimeException implements ExitCodeGenerator {

    private static final long serialVersionUID = 4346795684277246983L;

    public CustomException() {
        super();
    }

    public CustomException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomException(String message) {
        super(message);
    }

    public CustomException(Throwable cause) {
        super(cause);
    }

    @Override
    public int getExitCode() {
        return 1;
    }
}
