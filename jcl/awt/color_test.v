module awt

fn test_color() {
	c := color(99, 199, 255)
	println('Hello: ' + c.str())
	println(c.to_gx())
}
