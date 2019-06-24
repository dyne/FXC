# FXC Simple Secret Sharing

<a href="https://www.dyne.org"><img
src="https://secrets.dyne.org/static/img/swbydyne.png"
alt="software by Dyne.org"
title="software by Dyne.org" class="pull-right"></a>


[![Build Status](https://travis-ci.org/dyne/FXC.svg?branch=master)](https://travis-ci.org/dyne/FXC)

[![Code Climate](https://codeclimate.com/github/dyne/FXC.png)](https://codeclimate.com/github/dyne/FXC)

[![Clojars Project](https://clojars.org/org.clojars.dyne/fxc/latest-version.svg)](https://clojars.org/org.clojars.dyne/fxc)

The "FXC" cryptographic protocol is used to split a secret string in multiple parts and to recover it using some of these parts (quorum). An functional application demonstrating its functionality is available at <a href="https://secrets.dyne.org">secrets.dyne.org

<img src="https://secrets.dyne.org/static/img/secret_ladies.jpg"
	alt="Simple Secret Sharing is fun"
	title="Simple Secret Sharing is fun">
</a>

The FXC protocol and its use case (mostly related to social digital currency) are explained in this document [Implementation of digital social currency infrastructure (D5.5)](http://dcentproject.eu/wp-content/uploads/2015/10/D5.5-Implementation-of-digital-social-currency-infrastructure-.pdf) produced as part of the research conducted in the [D-CENT project](http://dcentproject.eu).

# Usage

```clojure
(require 'fxc.core)
(def secret (fxc.core/generate :url 32))
(def shares (fxc.core/encode fxc.core/settings secret))
(fxc.core/decode fxc.core/settings shares)
```

Default configuration settings:
```clojure
{:salt "La gatta sul tetto che scotta",
 :description "FXC v1 (Simple Secret Sharing, Freecoin component)",
 :protocol "FXC1", :alphabet "ABCDEFGHJKLMNPQRSTUVWXYZ23456789",
 :quorum 3,
 :prime prime4096,
 :type "WEB",
 :total 5,
 :max 1024,
 :length 6,
 :entropy 3.1}
```

Public functions:

- Encode
```clojure
fxc.core/encode
([conf pass])
  Takes a string and returns multiple strings that can be used to
  retrieve the original according to settings.
```
- Decode
```clojure
fxc.core/decode
([conf slices])
  Takes a collection of strings and returns the original secret
  according to the settings.
```
- Generate
```clojure
fxc.core/generate
([type size])
  Generates a random password of type and size. Available types
  are :bytes :base64 :base32 :hex and :url
```




# Acknowledgments

<img src="https://github.com/dyne/FXC/blob/master/doc/Haarlemsche_ABC.jpg"
	alt="Haarlem's Alphabet textile, from a Vlisco exhibition"
	title="Haarlem's Alphabet textile, from a Vlisco exhibition"
	style="float: right">

Industry standard addressed: Information technology -- Security techniques -- Secret sharing
- [ISO/IEC 19592-1:2016](https://www.iso.org/standard/65422.html) (Part 1: General)
- [ISO/IEC FDIS 19592-2 (Under development)](https://www.iso.org/standard/65425.html) (Part 2: Fundamental mechanisms)

The [Secret Sharing](https://en.wikipedia.org/wiki/Secret_sharing) algorithm adopted is based on [Shamir's Secret Sharing](https://en.wikipedia.org/wiki/Shamir%27s_Secret_Sharing), references:
- Shamir, Adi (1979), "How to share a secret", Communications of the ACM 22 (11): 612–613
- Knuth, D. E. (1997), The Art of Computer Programming, II: Seminumerical Algorithms: 505

The implementation used is by Tim Tiemens with a 4096 cipher prime number. The Integer Compression algorithm used internally is the FastPFOR128 by Daniel Lemire, see: Lemire, D. and Boytsov, L. "[Decoding billions of integers per second through vectorization](http://arxiv.org/abs/1209.2137)" (2015).

## License

FXC is Copyright (C) 2015-2019 by the Dyne.org Foundation

Designed, written and maintained by Denis Roio <jaromil@dyne.org>

This project is licensed under the AGPL 3 License - see the [LICENSE](LICENSE) file for details

#### Additional permission under GNU AGPL version 3 section 7.

``` 
If you modify FXC, or any covered work, by linking or combining it with any library (or a modified version of that library), containing parts covered by the terms of EPL v 1.0, the licensors of this Program grant you additional permission to convey the resulting work. Your modified version must prominently offer all users interacting with it remotely through a computer network (if your version supports such interaction) an opportunity to receive the Corresponding Source of your version by providing access to the Corresponding Source from a network server at no charge, through some standard or customary means of facilitating copying of software. Corresponding Source for a non-source form of such a combination shall include the source code for the parts of the libraries (dependencies) covered by the terms of EPL v 1.0 used as well as that of the covered work.
```
