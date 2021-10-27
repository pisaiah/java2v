// J2V Classlib - jcl.io.File
module jcl_io

import os

pub const path_separator = ';'
pub const file_separator = '/'

struct File {
	name string
	path string
}

pub fn file(name string) File {
	return File{
		name: name
		path: name
	}
}

pub fn file2(name string, path string) File {
	return File{
		name: name
		path: path
	}
}

/**
 * Return the name of the File
 */
pub fn (f File) get_name() string {
	return f.name
}

pub fn (f File) to_path() File {
	return f
}

pub fn (f File) get_path() string {
	return f.path
}

/**
 * Retrieve an array of strings of the files
 * and directories currently in this path.
 */
pub fn (f File) list() []string {
	return os.ls(f.get_path()) or { panic(err) }
}

/**
 * Retrieve an array of Files of the files
 * and directories currently in this path.
 */
pub fn (f File) list_files() []File {
	mut a := f.list()
	mut b := []File{}
	for str in a {
		b << file2(str, f.get_parent() + file_separator + str)
	}
	return b
}

/**
 */
pub fn (f File) get_absolute_path() string {
	return os.real_path(f.get_path())
}

/**
 */
pub fn (f File) get_parent() string {
	return f.get_absolute_path().replace( os.file_ext(f.get_absolute_path()), "")
}

/**
 * Tests whether the file denoted by this
 * name is a directory or not.
 */
pub fn (f File) is_directory() bool {
	return os.is_dir(f.get_path())
}

/**
 */
pub fn (f File) mkdir() bool {
	os.mkdir_all(f.get_path()) or { println('error') println(err) }
	return true
}

/**
 */
pub fn (f File) delete() bool {
	if f.is_directory() {
		os.rmdir(f.get_absolute_path()) or { return false }
	} else {
		os.rm(f.get_absolute_path()) or { return false }
	}
    return true
}