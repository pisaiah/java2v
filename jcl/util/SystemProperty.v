module jcl_util

import os

pub fn get_system_property(key string) string {
	env := os.environ()

	// Java Properties
	if key == 'java.version' {
		return '1.8.0_302'
	}
	if key == 'java.vendor' {
		return 'OpenJDK'
	}
	if key == 'java.vendor.url' {
		return 'https://example.com'
	}
	if key == 'java.vm.specification.name' {
		return 'Java Virtual Machine Specification'
	}

	// JRE home directory
	if key == 'java.home' {
		if 'JAVA_HOME' in env {
			return env['JAVA_HOME']
		}
		return 'C:\\Fake\\Path'
	}

	//
	if key == 'java.vm.version' {
		return 'Java2V indev'
	}

	// OS Name
	if key == 'os.name' {
		os := $if windows {
			'Windows 10'
		} $else $if macos {
			'Mac OS X'
		} $else $if ios || android {
			'Android'
		} $else {
			'Linux'
		}
		return os
	}

	if key == 'os.arch' {
		return 'AMD64'
	}

	// Directories
	if key == 'java.io.tmpdir' {
		return os.temp_dir()
	}
	if key == 'user.home' {
		return os.home_dir()
	}
	if key == 'line.separator' {
		return '\n'
	}

	return key
}
