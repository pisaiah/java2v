module jcl_nio

import jcl.io
import os

pub fn read_all_lines(f io.File) []string {
	return os.read_lines(f.get_absolute_path()) or { [""] }
}

pub fn write(arg0 io.File, b []byte) {
	mut str := unsafe { tos(b.data, b.len) }
	os.write_file(arg0.get_name(), str) or {}
}

pub fn copy(from io.File, to io.File) {
	mut from_bytes := os.read_bytes(from.get_name()) or { panic(err) }
	write(to, from_bytes)
}