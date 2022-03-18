module jcl_util

import encoding.base64

struct Base64_Decoder {
}

struct Base64_Encoder {
}

pub fn get_decoder() Base64_Decoder {
	return Base64_Decoder{}
}

pub fn get_encoder() Base64_Encoder {
	return Base64_Encoder{}
}

/**
 * Decodes all bytes from input array using Base64,
 * writing results into a new output byte array
*/
pub fn (b Base64_Decoder) decode(src []byte) []byte {
	mut str := unsafe { tos(src.data, src.len) }
	return base64.decode(str)
}

/**
 * Encode all bytes from input array using Base64,
 * writing results into a new output byte array
*/
pub fn (b Base64_Encoder) encode(src []byte) []byte {
	mut str := unsafe { tos(src.data, src.len) }
	return base64.decode(str)
}
