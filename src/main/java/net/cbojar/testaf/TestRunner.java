package net.cbojar.testaf;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

public final class TestRunner {
	private static final ClassLoader CLASSLOADER = TestRunner.class.getClassLoader();

	private final ClassLoader classLoader;

	public static void main(final String... args) {
		final TestRunner testRunner = new TestRunner(CLASSLOADER);
		testRunner.runTests();
	}

	private TestRunner(final ClassLoader classLoader) {
		this.classLoader = classLoader;
	}

	public void runTests() {
		try {
			System.out.println("Running tests...");
			getAllTestClassNames()
				.map((className) -> classFor(className))
				.filter(Optional::isPresent)
				.map(Optional::get)
				.peek(System.out::println)
				.forEach((clazz) -> runTestMethodsOn(clazz));
		} catch (SecurityException | URISyntaxException ex) {
			throw new TestAFException(ex);
		}
	}

	private Stream<String> getAllTestClassNames() throws URISyntaxException {
		if (!(classLoader instanceof URLClassLoader)) {
			throw new RuntimeException("Classloader is not a URL classloader");
		}

		return Arrays.stream(((URLClassLoader)classLoader).getURLs())
			.map((url) -> urlToFile(url))
			.flatMap((url) -> allTestClassNamesIn(url).stream());
	}

	private static File urlToFile(final URL url) {
		try {
			return Paths.get(url.toURI()).toFile();
		} catch (final URISyntaxException ex) {
			throw new TestAFException(ex);
		}
	}

	private static List<String> allTestClassNamesIn(final File root) {
		final List<String> classNames = new ArrayList<>();
		final Deque<File> directories = new ArrayDeque<>();

		directories.offer(root);

		while (!directories.isEmpty()) {
			final File currentDirectory = directories.poll();
			final File[] subFiles = currentDirectory.listFiles();

			if (subFiles == null) {
				continue;
			}

			for (final File subFile : subFiles) {
				if (isTestFile(subFile)) {
					classNames.add(classNameFrom(root, subFile));
				} else if (subFile.isDirectory()) {
					directories.offer(subFile);
				}
			}
		}

		return classNames;
	}

	private static boolean isTestFile(final File file) {
		return file.isFile() && file.getName().endsWith("TestAF.class");
	}

	private static String classNameFrom(final File root, final File file) {
		final String path = file.getPath();

		return path
			.substring(
					root.getPath().length() + 1,
					path.length() - ".class".length())
			.replace('/', '.');
	}

	private Optional<Class<?>> classFor(final String className) {
		Class<?> clazz = null;

		try {
			clazz = Class.forName(className, false, classLoader);
		} catch (final ClassNotFoundException ex) {
			return Optional.empty();
		}

		if (clazz.isAnnotation() || clazz.isEnum() || clazz.isInterface()) {
			return Optional.empty();
		}

		return Optional.of(clazz);
	}

	private static <T> void runTestMethodsOn(final Class<T> clazz) {
		for (final Method method : clazz.getMethods()) {
			if (!method.isAnnotationPresent(Test.class)) {
				continue;
			}

			final Test testAnnotation = method.getAnnotation(Test.class);
			final Class<? extends Throwable> expectedThrown = testAnnotation.thrown();

			try {
				System.err.println(method);
				final T instance = clazz.newInstance();
				method.invoke(instance);

				if (!expectedThrown.equals(NoExpectedException.class)) {
					System.out.printf("Test failed: %s%n", method.getName());
					new AssertionError(String.format("Expected exception %s not thrown", expectedThrown)).printStackTrace(System.out);
				}
			} catch (final InstantiationException | IllegalAccessException | IllegalArgumentException e) {
				e.printStackTrace();
			} catch (final InvocationTargetException ex) {
				if (expectedThrown.isInstance(ex.getCause())) {
					continue;
				} else if (ex.getCause() instanceof AssertionError) {
					System.out.printf("Test failed: %s%n", method.getName());
					ex.getCause().printStackTrace(System.out);
				} else {
					ex.printStackTrace();
				}
			}
		}
	}
}
