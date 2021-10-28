module jcl_nio

import jcl.io
import os

/**
 * Read all lines from an File and
 * return string array of those lines
 */
pub fn read_all_lines(f io.File) []string {
	return os.read_lines(f.get_absolute_path()) or { [""] }
}

/**
 * Write bytes to File overwriting
 * existing content.
 */
pub fn write(arg0 io.File, b []byte) {
	mut str := unsafe { tos(b.data, b.len) }
	os.write_file(arg0.get_name(), str) or {}
}

/**
 * Copy file to target file
 */
pub fn copy(from io.File, to io.File) {
	mut from_bytes := os.read_bytes(from.get_name()) or { panic(err) }
	write(to, from_bytes)
}

/**
 * Delete a file
 */
pub fn delete(target io.File) {
	target.delete()
}

/**
 * Size
 */
pub fn size(target io.File) f64 {
	return os.file_size(target.get_absolute_path())
}