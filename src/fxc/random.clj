;; Freecoin - digital social currency toolkit

;; part of Decentralized Citizen Engagement Technologies (D-CENT)
;; R&D funded by the European Commission (FP7/CAPS 610349)

;; Copyright (C) 2015 Dyne.org foundation
;; Copyright (C) 2015 Thoughtworks, Inc.

;; Sourcecode designed, written and maintained by
;; Denis Roio <jaromil@dyne.org>
;; Gareth Rogers <grogers@thoughtworks.com>

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

(ns fxc.random
  (:gen-class)
  (:import  [java.security SecureRandom]))

(defn digit
  "Generate a single random digit in the range of 0-9"
  [max]
  (.nextInt (java.security.SecureRandom.) max))

(defn intchain
  "Generate a string chaining digits up to length"
  [length]
  (loop [x    length
         ; make sure the first digit is not a zero
         res (int (digit 9))]
    (if (> x 1)
      (recur (dec x) (str res (digit 10)))
      res)))

(defn entropy
  "Measure (Shannon) the entropy of a string (returns a float)"
  [s]
  (let [len (count s), log-2 (Math/log 2)]
    (->> (frequencies s)
         (map (fn [[_ v]]
                (let [rf (/ v len)]
                  (-> (Math/log rf) (/ log-2) (* rf) Math/abs))))
         (reduce +))))

(defn create
  "Create a random `BigInteger' of the given length,
returning a map with keys [:integer :string]"
  [length]
  (let [res (intchain length)]
    {:integer (biginteger res)
     :string (str res)}))
