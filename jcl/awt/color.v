module awt

import gx

// java.awt.Color
// https://docs.oracle.com/javase/8/docs/api/java/awt/Color.html
struct Color {
	r int
	g int
	b int
}

// Creates an opaque sRGB color with the specified red, green, and blue values in the range (0 - 255).
pub fn color(r int, g int, b int) &Color {
	return &Color{r, g, b}
}

// Convert Color to gx.Color
pub fn (this &Color) to_gx() gx.Color {
	return gx.rgb(byte(this.r), byte(this.g), byte(this.b))
}

// Convert gx.Color to Color
pub fn from_gx(gxc gx.Color) &Color {
	return color(gxc.r, gxc.g, gxc.b)
}

// Returns the red component in the range 0-255 in the default sRGB space.
pub fn (this &Color) get_red() int {
	return this.r
}

// Returns the green component in the range 0-255 in the default sRGB space.
pub fn (this &Color) get_green() int {
	return this.g
}

// Returns the blue component in the range 0-255 in the default sRGB space.
pub fn (this &Color) get_blue() int {
	return this.b
}
