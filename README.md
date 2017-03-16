# FXC Simple Secret Sharing

<a href="https://www.dyne.org"><img
	src="https://secrets.dyne.org/static/img/swbydyne.png"
		alt="software by Dyne.org"
			title="software by Dyne.org" class="pull-right"></a>

[![Build Status](https://travis-ci.org/dyne/fxc.svg?branch=master)](https://travis-ci.org/dyne/fxc)

[![Clojars Project](https://img.shields.io/clojars/v/org.clojars.dyne/fxc.svg)](https://clojars.org/org.clojars.dyne/fxc)

The "FXC" cryptographic protocol is used to split a secret string in multiple parts and to recover it using some of these parts (quorum). The FXC protocol and its use case (mostly related to social digital currency) are explained in this document [Implementation of digital social currency infrastructure (D5.5)](http://dcentproject.eu/wp-content/uploads/2015/10/D5.5-Implementation-of-digital-social-currency-infrastructure-.pdf) produced as part of the research conducted in the [D-CENT project](http://dcentproject.eu).

# Acknowledgments

Industry standard addressed: Information technology -- Security techniques -- Secret sharing
- [ISO/IEC 19592-1:2016](https://www.iso.org/standard/65422.html) (Part 1: General)
- [ISO/IEC FDIS 19592-2 (Under development)](https://www.iso.org/standard/65425.html) (Part 2: Fundamental mechanisms)

The [Secret Sharing](https://en.wikipedia.org/wiki/Secret_sharing) algorithm adopted is based on [Shamir's Secret Sharing](https://en.wikipedia.org/wiki/Shamir%27s_Secret_Sharing), references:
- Shamir, Adi (1979), "How to share a secret", Communications of the ACM 22 (11): 612–613
- Knuth, D. E. (1997), The Art of Computer Programming, II: Seminumerical Algorithms: 505

The implementation used is by Tim Tiemens with a 4096 cipher prime number. The Integer Compression algorithm used internally is the FastPFOR128 by Daniel Lemire, see: Lemire, D. and Boytsov, L. "[Decoding billions of integers per second through vectorization](http://arxiv.org/abs/1209.2137)" (2015).

## License

FXC is Copyright (C) 2015-2017 by the Dyne.org Foundation

Designed, written and maintained by Denis Roio <jaromil@dyne.org>

```
This program is free software: you can redistribute it and/or modify
it under the terms of the GNU Affero General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU Affero General Public License for more details.

You should have received a copy of the GNU Affero General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.
```
