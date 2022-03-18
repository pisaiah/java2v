module jcl_util

import rand

struct UUID {
	value string
}

pub fn random_uuid() UUID {
	return UUID{
		value: rand.uuid_v4()
	}
}

pub fn (u UUID) str() string {
	return u.value
}
