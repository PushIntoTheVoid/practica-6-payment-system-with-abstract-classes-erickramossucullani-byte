package tests;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

final class TestSupport {

    private TestSupport() {
    }

    static CapturedResult captureSystemOutput(Runnable action) {
        PrintStream originalOut = System.out;
        PrintStream originalErr = System.err;
        ByteArrayOutputStream stdoutBuffer = new ByteArrayOutputStream();
        ByteArrayOutputStream stderrBuffer = new ByteArrayOutputStream();

        try (PrintStream stdout = new PrintStream(stdoutBuffer, true, StandardCharsets.UTF_8);
                PrintStream stderr = new PrintStream(stderrBuffer, true, StandardCharsets.UTF_8)) {
            System.setOut(stdout);
            System.setErr(stderr);
            action.run();
        } finally {
            System.setOut(originalOut);
            System.setErr(originalErr);
        }

        return new CapturedResult(stdoutBuffer.toString(StandardCharsets.UTF_8),
                stderrBuffer.toString(StandardCharsets.UTF_8));
    }

    static String readSource(String relativePath) throws Exception {
        Path path = Path.of(relativePath);
        assertTrue(Files.exists(path), "Expected source file to exist: " + relativePath);
        String content = Files.readString(path, StandardCharsets.UTF_8);
        assertNotNull(content);
        return content;
    }

    static int countOccurrences(String source, String fragment) {
        int count = 0;
        int index = 0;
        while ((index = source.indexOf(fragment, index)) >= 0) {
            count++;
            index += fragment.length();
        }
        return count;
    }

    static void invokeStore(Method method, Object target, Object... args) {
        try {
            method.invoke(target, args);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
    }

    static final class CapturedResult {
        final String stdout;
        final String stderr;

        CapturedResult(String stdout, String stderr) {
            this.stdout = stdout;
            this.stderr = stderr;
        }
    }
}
