module util

import os

fn test_() {
	for key, val in os.environ() {
		println(key + ' = ' + val)
	}
	println(os.user_os())
}
