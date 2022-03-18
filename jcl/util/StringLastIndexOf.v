module jcl_util

import os

// util.v
struct StringLastIndexOf {
}

pub fn last_index_of(sa string, sb string) int {
	return sa.last_index(sb) or { -1 }
}
