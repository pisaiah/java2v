# Java2V
Java2V Transpiler (J2V) provides the ability to transpile Java source into human-readable V that can be compiled using V's C, or Javascript backends.
A subset of the Java Class Library is also implemented for use.

[See Downloads](https://github.com/IsaiahPatton/java2v/releases)

## Example

Input:
```java
public static void main(String[] args) {
	File file = new File("data.txt");
	System.out.println("File name: " + file.getName());

	List<String> content = Files.readAllLines(file.toPath());
  	for (String line : content) {
		System.out.println(line);
  	}
}
```

Output:
```v
fn main() {
	mut file := io.file("data.txt")
	println("File name: " + file.get_name())

	mut content := nio.read_all_lines(file.to_path())
	for mut line in content {
		println(line)
	}
}
```

## Repo Structure

There are two modules in this repository.

- ``jcl/`` - Classes from the [Java Class Library](https://docs.oracle.com/javase/8/docs/api/) reimplemented in V.
- ``src/`` - Main transpiler source. Currently in early development and needs polishing before public source release.
