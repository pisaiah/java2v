module util

import rand

// Random
// https://docs.oracle.com/javase/8/docs/api/java/util/Random.html
struct Random {
}

pub fn random() &Random {
	return &Random{}
}

pub fn random_with_seed() &Random {
	return &Random{}
}

// Returns the next pseudorandom, uniformly distributed float value between 0.0 and 1.0
pub fn (random &Random) next_float() {
	return rand.f64()
}

// Returns the next pseudorandom, uniformly distributed int value
pub fn (random &Random) next_int() {
	return rand.int()
}
