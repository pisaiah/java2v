module jcl_util_zip

import jcl.io

pub const open_read = 0

struct ZipFile {
    file io.File
}

pub fn zipfile(file io.File) ZipFile {
    return ZipFile{
        file: file
    }
}