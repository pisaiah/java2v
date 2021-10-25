module jcl_util

import os

pub fn get_system_property(key string) string {
	if key == "java.version" {
        return "1.8.0_302"
	}
    if key == "java.vendor" {
        return "OpenJDK"
    }
    if key == "java.vendor.url" {
        return "https://example.com"
    }
    if key == "java.home" {
        return "C:\\Fake\\Path"
    }
    if key == "java.vm.version" {
        return "Java2V indev"
    }
    if key == "os.name" {
        os := $if windows { "Windows 10" } $else $if macos { "Mac OS X" } 
                $else $if ios || android { "Android" } $else { "Linux" }
        return os
    }
    if key == "java.io.tmpdir" {
        return os.temp_dir()
    }
    if key == "user.home" {
        return os.home_dir()
    }
	return key
} 