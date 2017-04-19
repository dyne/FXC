;; FXC -  Secret Sharing

;; part of Decentralized Citizen Engagement Technologies (D-CENT)
;; R&D funded by the European Commission (FP7/CAPS 610349)

;; Copyright (C) 2015-2017 Dyne.org foundation

;; Sourcecode designed, written and maintained by
;; Denis Roio <jaromil@dyne.org>

;; This program is free software: you can redistribute it and/or modify
;; it under the terms of the GNU Affero General Public License as published by
;; the Free Software Foundation, either version 3 of the License, or
;; (at your option) any later version.

;; This program is distributed in the hope that it will be useful,
;; but WITHOUT ANY WARRANTY; without even the implied warranty of
;; MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
;; GNU Affero General Public License for more details.

;; You should have received a copy of the GNU Affero General Public License
;; along with this program.  If not, see <http://www.gnu.org/licenses/>.

(ns fxc.intcomp
  (:gen-class)
  (:import [me.lemire.integercompression IntWrapper
            IntCompressor BinaryPacking FastPFOR
            Composition VariableByte FastPFOR128 IntegerCODEC]
           [me.lemire.integercompression.differential
            IntegratedIntegerCODEC IntegratedComposition
            IntegratedBinaryPacking IntegratedVariableByte]))


(defn codec.binpack []
  (IntegratedComposition. (IntegratedBinaryPacking.) (IntegratedVariableByte.)))

(defn codec.pfor128 []
  (Composition. (FastPFOR128.) (VariableByte.)))

(defn codec.pfor []
  (Composition. (FastPFOR.) (VariableByte.)))

(defn compress
  ([intarr] 
   (seq (.compress (IntCompressor.) (int-array intarr))))

  ;; non-default codecs used only for debugging
  ([cx intarr]
   (let [len (count intarr)
         res (int-array (repeat len 0))
         inpos  0
         outpos 0 ]
     (.compress cx (int-array intarr) (IntWrapper. inpos) len res (IntWrapper. outpos))
  {:codec (str cx)
   :inpos inpos
   :outpos outpos
   :data res})))


(defn decompress
  ([intarr] (seq (.uncompress (IntCompressor.) (int-array intarr))))

  ;; non-default codecs used only for debugging
  ([cx intarr]
   (let [len (count intarr)
         res (int-array (repeat len 0))
         inpos  0
         outpos 0 ]
     (.uncompress cx (int-array intarr) (IntWrapper. inpos) len res (IntWrapper. outpos))
     {:codec (str cx)
      :inpos inpos
      :outpos outpos
      :data res})))

